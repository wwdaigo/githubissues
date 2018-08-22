package io.wwdaigo.githubissues.viewmodels

import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.githubissues.commons.Errors
import io.wwdaigo.githubissues.domain.IssueState
import io.wwdaigo.githubissues.modules.list.data.IssueListData
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModel
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModelImpl
import io.wwdaigo.githubissues.support.IssueList
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class ListIssuesViewModelTests {

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

    @Test
    fun testListOpenIssues() {
        val test = TestSubscriber<IssueListData>()

        doAnswer {
            Flowable.just(IssueListData(
                    listOf(IssueList.openIssue1,
                            IssueList.openIssue2),
                    false
            )).subscribe(test)
        }.whenever(inputs)
                .listCurrentPage(IssueState.OPEN)

        inputs.listCurrentPage(IssueState.OPEN)

        test.assertSubscribed()
        test.assertComplete()
        test.assertNoErrors()
        test.assertValue {
            !it.shouldClear
            it.list.size == 2
            it.list.first() == IssueList.openIssue1
            it.list.last() == IssueList.openIssue2
        }
    }

    @Test
    fun testErrorResponse() {
        val test = TestSubscriber<Errors>()

        doAnswer {
            Flowable.just(Errors.SERVER_ERROR)
                    .subscribe(test)
        }.whenever(inputs).listNextPage(IssueState.OPEN)

        inputs.listNextPage(IssueState.OPEN)

        test.assertSubscribed()
        test.assertComplete()
        test.assertNoErrors()
        test.assertValue(Errors.SERVER_ERROR)
        test.assertNever(Errors.NO_INTERNET)
    }
}