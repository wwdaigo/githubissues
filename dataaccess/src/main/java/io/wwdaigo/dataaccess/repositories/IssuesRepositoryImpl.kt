package io.wwdaigo.dataaccess.repositories

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.wwdaigo.common.exceptions.GetIssueFailed
import io.wwdaigo.common.exceptions.ListIssuesFailed
import io.wwdaigo.dataaccess.api.RestApi
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.domain.entities.IssueState
import io.wwdaigo.domain.repositories.IssuesRepository

class IssuesRepositoryImpl(context: Context): IssuesRepository {

    private val service = RestApi(context).githubServices

    override fun getIssue(id: Int): Flowable<Issue> {
        return service.getIssues(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.response() }
                .map {
                    if (it.isSuccessful) {
                        it.body() ?: throw GetIssueFailed()
                    } else {
                        throw GetIssueFailed()
                    }
                }
    }

    override fun listIssues(state: IssueState, page: Int): Flowable<List<Issue>> {
        return service.listIssues(state.name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.response() }
                .map {
                    if (it.isSuccessful) {
                        it.body() ?: throw ListIssuesFailed()
                    } else {
                        throw ListIssuesFailed()
                    }
                }
    }
}