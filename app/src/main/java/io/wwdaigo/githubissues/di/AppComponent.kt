package io.wwdaigo.githubissues.di

import dagger.BindsInstance
import dagger.Component
import io.wwdaigo.githubissues.commons.App
import io.wwdaigo.githubissues.di.modules.ActivitiesModule
import io.wwdaigo.githubissues.di.modules.AppModule
import io.wwdaigo.githubissues.di.modules.ServicesModule
import io.wwdaigo.githubissues.di.modules.ViewModelsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (ActivitiesModule::class), (ServicesModule::class), (ViewModelsModule::class)])
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}