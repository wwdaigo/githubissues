package io.wwdaigo.githubissues.managers

import io.reactivex.Flowable
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.githubissues.api.managers.GithubManagerImpl
import io.wwdaigo.githubissues.api.services.GithubServices
import io.wwdaigo.githubissues.domain.Issue
import io.wwdaigo.githubissues.support.IssueList
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class GithubManagerTests {

    private val ERROR_GET = "Error at Github Manager on GET Method"
    private val ERROR_LIST = "Error at Github Manager on LIST Method"

    @Rule @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    lateinit var githubManager: GithubManager

    @Mock
    lateinit var service: GithubServices

    @InjectMocks
    lateinit var githubManagerImpl: GithubManagerImpl

    @Test
    fun testImplementation() {
        assert(githubManagerImpl is GithubManager)
        assertNotNull(service)
        assertNotNull(githubManager)
    }

    @Test
    fun testListIssues() {
        val result: Result<List<Issue>> = Result.response(Response.success(
                listOf(
                        IssueList.openIssue1,
                        IssueList.openIssue2)))

        `when`(githubManager.listIssues("", 0))
                .thenReturn(Flowable.just(result))

        val testObserver = githubManager.listIssues("", 0).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertSubscribed()
        testObserver.assertValue {
            val response = it.response()!!
            response.isSuccessful
            response.body()!!.size == 2
            response.body()!!.first() == IssueList.openIssue1
            response.body()!!.last() == IssueList.openIssue2
        }
        testObserver.assertNever {
            val body = it.response()!!.body()!!
            body.first() == IssueList.closedIssue1
            body.size != 2
        }
    }

    @Test
    fun testGetIssue() {
        val result: Result<Issue> = Result.response(Response.success(
                        IssueList.closedIssue1))

        val notResult: Result<Issue> = Result.response(Response.success(
                IssueList.openIssue1))

        `when`(githubManager.getIssue(0))
                .thenReturn(Flowable.just(result))

        val testObserver = githubManager.getIssue( 0).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertSubscribed()
        testObserver.assertValue(result)
        testObserver.assertNever(notResult)
    }

    @Test
    fun testEmptyGetIssue() {
        `when`(githubManager.getIssue(0)).thenReturn(Flowable.empty())

        val testObserver = githubManager.getIssue(0).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertSubscribed()
        testObserver.assertNoValues()
    }

    @Test
    fun testEmptyListGetResources() {
        val result: Result<List<Issue>> = Result.response(Response.success(
                listOf()))

        `when`(githubManager.listIssues("", 0)).thenReturn(Flowable.just(result))

        val testObserver = githubManager.listIssues("", 0).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertSubscribed()
        testObserver.assertValue {
            it.response()?.body()?.size == 0
        }
    }

    @Test
    fun testErrorOnListIssues() {
        `when`(githubManager.listIssues("",0)).thenReturn(Flowable.error(Throwable(ERROR_LIST)))

        val testObserver = githubManager.listIssues("",0).test()

        testObserver.assertSubscribed()
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
        testObserver.assertErrorMessage(ERROR_LIST)
        testObserver.assertError { it.message != ERROR_GET }
    }

    @Test
    fun testErrorOnGetResource() {
        `when`(githubManager.getIssue(0)).thenReturn(Flowable.error(Throwable(ERROR_GET)))

        val testObserver = githubManager.getIssue(0).test()

        testObserver.assertSubscribed()
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
        testObserver.assertErrorMessage(ERROR_GET)
        testObserver.assertError { it.message != ERROR_LIST }
    }
}