package io.wwdaigo.githubissues.modules.list.viewmodels

import io.reactivex.Flowable
import io.reactivex.Observable
import io.wwdaigo.domain.entities.IssueState
import io.wwdaigo.githubissues.commons.BaseViewModel
import io.wwdaigo.githubissues.modules.list.data.IssueListData

interface ListIssuesViewModel: BaseViewModel {
    val callbacks: Callbacks

    fun list(state: IssueState, page: Int)
    fun listCurrentPage(state: IssueState)
    fun listNextPage(state: IssueState)

    interface Callbacks{
        val isLoading: Observable<Boolean>
        val isRefreshing: Observable<Boolean>
        val errorMessage: Observable<String>
        val listIssues: Observable<IssueListData>
    }
}