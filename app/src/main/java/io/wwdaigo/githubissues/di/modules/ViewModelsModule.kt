package io.wwdaigo.githubissues.di.modules

import dagger.Module
import dagger.Provides
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModel
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModelImpl

@Module
class ViewModelsModule {

    @Provides
    fun providesListIssuesViewModel(impl: ListIssuesViewModelImpl): ListIssuesViewModel {
        return impl
    }
}