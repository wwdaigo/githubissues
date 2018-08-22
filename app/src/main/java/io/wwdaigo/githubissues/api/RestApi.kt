package io.wwdaigo.githubissues.api

import android.content.Context
import io.wwdaigo.githubissues.api.interceptors.RestInterceptor
import io.wwdaigo.githubissues.api.services.GithubServices
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Singleton
class RestApi(context: Context, httpUrl: HttpUrl) {

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RestInterceptor(context))
            .build()

    private val retrofitService = Retrofit.Builder()
            .baseUrl(httpUrl)
                .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    // Services
    val githubServices = retrofitService.create(GithubServices::class.java)
}