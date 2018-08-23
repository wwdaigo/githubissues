package io.wwdaigo.githubissues.viewmodels

import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.githubissues.commons.Errors
import io.wwdaigo.githubissues.domain.Issue
import io.wwdaigo.githubissues.domain.IssueState
import io.wwdaigo.githubissues.modules.list.data.IssueListData
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModel
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModelImpl
import io.wwdaigo.githubissues.support.IssueList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class ListIssuesViewModelTests {

    private val ERROR = "Error at Github View Model"

    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    lateinit var listIssuesViewModel: ListIssuesViewModel

    @Mock
    lateinit var inputs: ListIssuesViewModel.Inputs

    @Mock
    lateinit var outputs: ListIssuesViewModel.Outputs

    @Mock
    lateinit var manager: GithubManager

    @InjectMocks
    lateinit var listIssuesViewModelImpl: ListIssuesViewModelImpl

    @Before
    fun setup() {
        doAnswer {
            Flowable.just(Result.response(Response.success(
                    listOf(IssueList.openIssue1,
                            IssueList.openIssue2))))
        }.whenever(manager)
                .listIssues("open", 0)

        doAnswer {
            Flowable.just(Result.error<Throwable>(Throwable(ERROR)))
        }.whenever(manager)
                .listIssues("closed", 0)
    }

    @Test
    fun testListOpenIssues() {
        val test = TestSubscriber<IssueListData>()
        listIssuesViewModelImpl.outputs.listIssues.subscribe(test)

        listIssuesViewModelImpl.inputs.list(IssueState.OPEN,0)

        test.assertValue {
            !it.shouldClear
            it.list.size == 2
            it.list.first() == IssueList.openIssue1
            it.list.last() == IssueList.openIssue2
        }
    }

    @Test
    fun testListOpenIssuesError() {
        val test = TestObserver<Errors>()
        listIssuesViewModelImpl.outputs.errorStatus.subscribe(test)

        listIssuesViewModelImpl.inputs.list(IssueState.CLOSED, 0)

        test.assertValue(Errors.SERVER_ERROR)
    }
}