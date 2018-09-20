package io.wwdaigo.dataaccess.api.services

import io.reactivex.Flowable
import io.wwdaigo.domain.entities.Issue
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubServices {

    @GET("repos/tensorflow/tensorflow/issues")
    fun listIssues(@Query("state") state: String, @Query("page") page: Int): Flowable<Result<List<Issue>>>

    @GET("repos/tensorflow/tensorflow/issues/{id}")
    fun getIssues(@Path("id") id: Int): Flowable<Result<Issue>>
}