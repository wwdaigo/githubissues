package io.wwdaigo.dataaccess.api

import android.content.Context
import io.wwdaigo.dataaccess.api.interceptors.RestInterceptor
import io.wwdaigo.dataaccess.api.services.GithubServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RestApi(context: Context) {

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RestInterceptor(context))
            .build()

    private val retrofitService = Retrofit.Builder()
            .baseUrl(BACKEND_ROOT)
                .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    // Services
    val githubServices = retrofitService.create(GithubServices::class.java)

    companion object {
        const val BACKEND_ROOT = "https://api.github.com/"
        const val TOKEN = "51a28ecbc6888bc07d39c9e045fe1cc6c5b493f2"
    }
}