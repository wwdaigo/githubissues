package io.wwdaigo.githubissues.modules.list.viewmodels

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.githubissues.commons.Errors
import io.wwdaigo.githubissues.commons.viewmodel.BaseViewModel
import io.wwdaigo.githubissues.domain.IssueState
import io.wwdaigo.githubissues.modules.list.data.IssueListData
import javax.inject.Inject

class ListIssuesViewModelImpl @Inject constructor(): BaseViewModel(), ListIssuesViewModel, ListIssuesViewModel.Inputs, ListIssuesViewModel.Outputs {

    private var pageNumber = 1

    @Inject
    lateinit var githubManager: GithubManager

    override val inputs: ListIssuesViewModel.Inputs
        get() = this
    override val outputs: ListIssuesViewModel.Outputs
        get() = this

    // Outputs
    private val listIssuesPublish = PublishProcessor.create<IssueListData>()
    override val listIssues: Flowable<IssueListData>
        get() = listIssuesPublish

    // Inputs
    override fun list(state: IssueState, page: Int) {
        pageNumber = page

        isLoadingSubject.onNext(true)

        githubManager.listIssues(state.name.toLowerCase(), page)
                .subscribe({
                    isLoadingSubject.onNext(false)

                    it.response()?.body()?.let { list ->
                        listIssuesPublish.onNext(
                                IssueListData(list, (pageNumber == 1)))
                    }

                    errorHandling(it)

                }, { error ->
                    error.printStackTrace()
                    isLoadingSubject.onNext(false)
                    errorStatusSubject.onNext(Errors.SERVER_ERROR)
                })
    }

    override fun listCurrentPage(state: IssueState) {
        list(state, pageNumber)
    }

    override fun listNextPage(state: IssueState) {
        pageNumber++
        list(state, pageNumber)
    }
}