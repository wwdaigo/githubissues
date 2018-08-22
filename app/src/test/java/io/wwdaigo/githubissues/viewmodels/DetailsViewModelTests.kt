package io.wwdaigo.githubissues.viewmodels

import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.githubissues.commons.Errors
import io.wwdaigo.githubissues.domain.Issue
import io.wwdaigo.githubissues.domain.IssueState
import io.wwdaigo.githubissues.modules.detail.viewmodels.DetailViewModel
import io.wwdaigo.githubissues.modules.detail.viewmodels.DetailViewModelImpl
import io.wwdaigo.githubissues.modules.list.data.IssueListData
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModel
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModelImpl
import io.wwdaigo.githubissues.support.IssueList
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnit

class DetailsViewModelTests {

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

    @Test
    fun testGetIssue() {
        val test = TestSubscriber<Issue>()

        doAnswer {
            Flowable.just(IssueList.closedIssue1).subscribe(test)
        }.whenever(inputs)
                .getIssue(0)

        inputs.getIssue(0)

        test.assertSubscribed()
        test.assertComplete()
        test.assertNoErrors()
        test.assertValue(IssueList.closedIssue1)
        test.assertNever(IssueList.openIssue2)
    }
}