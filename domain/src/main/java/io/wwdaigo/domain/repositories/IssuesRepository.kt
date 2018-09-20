package io.wwdaigo.domain.repositories

import io.reactivex.Flowable
import io.reactivex.Observable
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.domain.entities.IssueState

interface IssuesRepository {
    fun listIssues(state: IssueState, page: Int): Flowable<List<Issue>>
    fun getIssue(id: Int): Flowable<Issue>
}