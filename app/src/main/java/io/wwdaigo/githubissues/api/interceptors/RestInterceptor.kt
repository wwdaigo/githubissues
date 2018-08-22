package io.wwdaigo.githubissues.api.interceptors

import android.content.Context
import io.wwdaigo.githubissues.commons.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class RestInterceptor(val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        val newRequest = request.newBuilder()
                .addHeader("Accept", "application/vnd.github.v3+json")
                .addHeader("Authorization", "Bearer $TOKEN")
                .build()

        return chain.proceed(newRequest)
    }
}