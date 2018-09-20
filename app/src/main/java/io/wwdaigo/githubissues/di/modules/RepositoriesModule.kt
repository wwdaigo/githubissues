package io.wwdaigo.githubissues.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.wwdaigo.dataaccess.repositories.IssuesRepositoryImpl
import io.wwdaigo.domain.repositories.IssuesRepository

@Module
class RepositoriesModule {

    @Provides
    fun providesIssuesRepository(context: Context): IssuesRepository {
        return IssuesRepositoryImpl(context)
    }
}