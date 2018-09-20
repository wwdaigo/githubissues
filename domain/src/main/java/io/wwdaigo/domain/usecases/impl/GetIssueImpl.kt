package io.wwdaigo.domain.usecases.impl

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.wwdaigo.domain.commons.Result
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.domain.repositories.IssuesRepository
import io.wwdaigo.domain.usecases.GetIssue

class GetIssueImpl(private val repository: IssuesRepository): GetIssue, GetIssue.Callback {
    override val callback: GetIssue.Callback
        get() = this

    private val issueSubject = PublishSubject.create<Result<Issue>>()
    override val issue: Observable<Result<Issue>>
        get() = issueSubject

    override fun getIssue(id: Int) {
        repository.getIssue(id)
                .doOnNext { issueSubject.onNext(Result.Success(it)) }
                .doOnError { issueSubject.onNext(Result.Error(it)) }
                .subscribe()
    }
}