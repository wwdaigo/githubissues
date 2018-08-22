package io.wwdaigo.githubissues.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

import io.wwdaigo.githubissues.commons.App

@Module
class AppModule(val app: App) {

    @Provides
    fun providesContext(): Context {
        return app.applicationContext
    }

    /*
    @Provides
    fun providesPreferencesManager(impl: PreferencesManagerImpl): PreferencesManager {
        return impl
    }

    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    }*/
}