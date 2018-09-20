package io.wwdaigo.githubissues.modules.list.viewmodels

import android.content.Context
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.wwdaigo.common.exceptions.ListIssuesFailed
import io.wwdaigo.domain.commons.Result
import io.wwdaigo.domain.entities.IssueState
import io.wwdaigo.domain.usecases.ListIssues
import io.wwdaigo.githubissues.R
import io.wwdaigo.githubissues.modules.list.data.IssueListData
import javax.inject.Inject

class ListIssuesViewModelImpl @Inject constructor(): ListIssuesViewModel, ListIssuesViewModel.Callbacks{

    private var pageNumber = 1

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var listIssuesUseCase: ListIssues

    override val callbacks: ListIssuesViewModel.Callbacks
        get() = this

    override fun start() {
        subscribeListIssuesCallback()
    }

    private val isLoadingSubject = PublishSubject.create<Boolean>()
    override val isLoading: Observable<Boolean>
        get() = isLoadingSubject

    private val isRefreshingSubject = PublishSubject.create<Boolean>()
    override val isRefreshing: Observable<Boolean>
        get() = isRefreshingSubject

    private val errorMessageSubject = PublishSubject.create<String>()
    override val errorMessage: Observable<String>
        get() = errorMessageSubject

    private val listIssuesPublish = PublishSubject.create<IssueListData>()
    override val listIssues: Observable<IssueListData>
        get() = listIssuesPublish

    // Inputs
    override fun list(state: IssueState, page: Int) {
        pageNumber = page

        publishStartLoadingStatus()
        listIssuesUseCase.listIssuesByState(state, page)
    }

    override fun listCurrentPage(state: IssueState) {
        list(state, pageNumber)
    }

    override fun listNextPage(state: IssueState) {
        pageNumber++
        list(state, pageNumber)
    }

    private fun subscribeListIssuesCallback() = with(listIssuesUseCase.callback.issues) {
        doOnNext {
            when (it) {
                is Result.Success -> {
                    val list = IssueListData(it.data, false)
                    listIssuesPublish.onNext(list)
                }
                is Result.Error -> publishErrorMessage(it.throwable)
            }
        }.doOnError {
            publishErrorMessage(it)
        }.subscribe {
            publishStopLoadingStatus()
        }
    }

    private fun publishStartLoadingStatus() {
        if (pageNumber == 1)
            isRefreshingSubject.onNext(true)
        else
            isLoadingSubject.onNext(true)
    }

    private fun publishStopLoadingStatus() {
        isRefreshingSubject.onNext(false)
        isLoadingSubject.onNext(false)
    }

    private fun publishErrorMessage(throwable: Throwable) {
        val message = when (throwable) {
            is ListIssuesFailed -> context.getString(R.string.list_issues_error)
            else -> context.getString(R.string.server_error)
        }

        errorMessageSubject.onNext(message)
    }
}