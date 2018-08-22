package io.wwdaigo.githubissues.modules.detail.viewmodels

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.githubissues.commons.viewmodel.BaseViewModel
import io.wwdaigo.githubissues.domain.Issue
import javax.inject.Inject

class DetailViewModelImpl @Inject constructor(): BaseViewModel(), DetailViewModel, DetailViewModel.Inputs, DetailViewModel.Outputs {

    @Inject
    lateinit var githubManager: GithubManager

    override val inputs: DetailViewModel.Inputs
        get() = this
    override val outputs: DetailViewModel.Outputs
        get() = this

    private val issuePublish = PublishProcessor.create<Issue>()
    override val issue: Flowable<Issue>
        get() = issuePublish


    override fun getIssue(number: Int) {
        isLoadingSubject.onNext(true)
        githubManager.getIssue(number)
                .subscribe({
                    isLoadingSubject.onNext(false)
                    it.response()?.body()?.let { body ->
                        issuePublish.onNext(body)
                    }

                    errorHandling(it)
                }, { error ->
                    error.printStackTrace()
                    isLoadingSubject.onNext(false)
                })
    }

}