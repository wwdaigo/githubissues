package io.wwdaigo.githubissues.di.modules

import dagger.Module
import dagger.Provides
import io.wwdaigo.githubissues.modules.detail.viewmodels.DetailViewModel
import io.wwdaigo.githubissues.modules.detail.viewmodels.DetailViewModelImpl
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModel
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModelImpl

@Module
class ViewModelsModule {

    @Provides
    fun providesListIssuesViewModel(impl: ListIssuesViewModelImpl): ListIssuesViewModel {
        return impl
    }

    @Provides
    fun providesDetailViewModel(impl: DetailViewModelImpl): DetailViewModel {
        return impl
    }
}