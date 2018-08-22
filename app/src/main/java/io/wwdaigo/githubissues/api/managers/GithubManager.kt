package io.wwdaigo.githubissues.api.managers

import io.reactivex.Flowable
import io.wwdaigo.githubissues.domain.Issue
import retrofit2.adapter.rxjava2.Result

interface GithubManager {
    fun listIssues(state: String, page: Int): Flowable<Result<List<Issue>>>
    fun getIssue(id: Int): Flowable<Result<Issue>>
}