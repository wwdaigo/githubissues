package io.wwdaigo.githubissues.api.managers

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.wwdaigo.githubissues.api.services.GithubServices
import io.wwdaigo.githubissues.domain.Issue
import retrofit2.adapter.rxjava2.Result
import javax.inject.Inject

class GithubManagerImpl @Inject constructor(var service: GithubServices): GithubManager {

    override fun listIssues(state: String, page: Int): Flowable<Result<List<Issue>>> {
        return service.listIssues(state, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getIssue(id: Int): Flowable<Result<Issue>> {
        return service.getIssues(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}