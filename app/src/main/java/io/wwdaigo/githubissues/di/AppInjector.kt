package io.wwdaigo.githubissues.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.wwdaigo.githubissues.commons.App
import io.wwdaigo.githubissues.di.modules.AppModule

class AppInjector private constructor() {

    companion object {
        fun init(app: App) {
            DaggerAppComponent.builder()
                    .application(app)
                    .appModule(AppModule(app))
                    .build()
                    .inject(app)
        }
    }
}