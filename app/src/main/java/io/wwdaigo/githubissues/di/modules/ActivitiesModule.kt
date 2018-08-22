package io.wwdaigo.githubissues.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.wwdaigo.githubissues.modules.detail.DetailsActivity

import io.wwdaigo.githubissues.modules.list.MainActivity
import io.wwdaigo.githubissues.modules.list.fragments.IssuesFragment

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailsActivityInjector(): DetailsActivity

    @ContributesAndroidInjector
    abstract fun contributeIssuesFragmentInjector(): IssuesFragment
}