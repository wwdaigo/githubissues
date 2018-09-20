package io.wwdaigo.domain.usecases.impl

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.wwdaigo.domain.commons.Result
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.domain.entities.IssueState
import io.wwdaigo.domain.repositories.IssuesRepository
import io.wwdaigo.domain.usecases.ListIssues

class ListIssuesImpl(private val repository: IssuesRepository): ListIssues, ListIssues.Callback {
    override val callback: ListIssues.Callback
        get() = this

    private val issuesSubject = PublishSubject.create<Result<List<Issue>>>()
    override val issues: Observable<Result<List<Issue>>>
        get() = issuesSubject

    override fun listIssuesByState(state: IssueState, page: Int) {
        repository.listIssues(state, page)
                .subscribe({
                    issuesSubject.onNext(Result.Success(it))
                }, {
                    issuesSubject.onNext(Result.Error(it))
                })
    }
}