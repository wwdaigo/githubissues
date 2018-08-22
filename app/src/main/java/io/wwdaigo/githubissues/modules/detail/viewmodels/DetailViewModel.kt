package io.wwdaigo.githubissues.modules.detail.viewmodels

import io.reactivex.Flowable
import io.wwdaigo.githubissues.commons.viewmodel.ViewModelOutput
import io.wwdaigo.githubissues.domain.Issue

interface DetailViewModel {

    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun getIssue(number: Int)
    }

    interface Outputs: ViewModelOutput {
        val issue: Flowable<Issue>
    }
}