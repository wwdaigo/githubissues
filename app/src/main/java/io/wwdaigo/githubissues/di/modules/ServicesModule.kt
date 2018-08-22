package io.wwdaigo.githubissues.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.wwdaigo.githubissues.commons.BACKEND_ROOT
import io.wwdaigo.githubissues.api.services.GithubServices
import io.wwdaigo.githubissues.api.RestApi
import io.wwdaigo.githubissues.api.managers.GithubManager
import io.wwdaigo.githubissues.api.managers.GithubManagerImpl
import okhttp3.HttpUrl
import javax.inject.Singleton

@Module
class ServicesModule {

    @Provides
    fun providesHttpUrl(): HttpUrl {
        return HttpUrl.parse(BACKEND_ROOT)!!
    }

    @Provides
    fun providesRestApi(context: Context, httpUrl: HttpUrl): RestApi {
        return RestApi(context, httpUrl)
    }

    @Singleton
    @Provides
    fun providesGithubServices(restApi: RestApi): GithubServices {
        return restApi.githubServices
    }

    @Singleton
    @Provides
    fun providesGithubManager(manager: GithubManagerImpl): GithubManager {
        return manager
    }
}