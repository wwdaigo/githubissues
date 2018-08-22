package io.wwdaigo.githubissues.modules.list.viewmodels

import io.reactivex.Flowable
import io.wwdaigo.githubissues.commons.viewmodel.ViewModelOutput
import io.wwdaigo.githubissues.domain.IssueState
import io.wwdaigo.githubissues.modules.list.data.IssueListData

interface ListIssuesViewModel {

    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun list(state: IssueState, page: Int)
        fun listCurrentPage(state: IssueState)
        fun listNextPage(state: IssueState)
    }

    interface Outputs: ViewModelOutput {
        val listIssues: Flowable<IssueListData>
    }
}