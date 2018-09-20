package io.wwdaigo.domain.usecases

import io.reactivex.Observable
import io.wwdaigo.domain.commons.Result
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.domain.entities.IssueState

interface ListIssues {
    val callback: Callback

    fun listIssuesByState(state: IssueState, page: Int)

    interface Callback {
        val issues: Observable<Result<List<Issue>>>
    }
}