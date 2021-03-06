package io.wwdaigo.githubissues.viewmodels

import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.githubissues.support.IssueList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class DetailsViewModelTests {

    private val ERROR = "Error at Github View Model"

    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    lateinit var detailViewModel: DetailViewModel

    @Mock
    lateinit var inputs: DetailViewModel.Inputs

    @Mock
    lateinit var outputs: DetailViewModel.Outputs

    @Mock
    lateinit var manager: GithubManager

    @InjectMocks
    lateinit var detailViewModelImpl: DetailViewModelImpl

    @Before
    fun setup() {
        doAnswer {
            Flowable.just(Result.response(Response.success(
                    IssueList.closedIssue1)))
        }.whenever(manager)
                .getIssue(0)

        doAnswer {
            Flowable.just(Result.error<Throwable>(Throwable(ERROR)))
        }.whenever(manager)
                .getIssue(1)
    }

    @Test
    fun testGetIssue() {
        val test = TestSubscriber<Issue>()
        detailViewModelImpl.outputs.issue.subscribe(test)

        detailViewModelImpl.inputs.getIssue(0)

        test.assertValue(IssueList.closedIssue1)
        test.assertNever(IssueList.openIssue2)
    }

    @Test
    fun testGetIssueError() {
        val test = TestObserver<Errors>()
        detailViewModelImpl.outputs.errorStatus.subscribe(test)

        detailViewModelImpl.inputs.getIssue(1)

        test.assertValue(Errors.SERVER_ERROR)
    }
}