package io.wwdaigo.githubissues.di.modules

import dagger.Module
import dagger.Provides
import io.wwdaigo.domain.repositories.IssuesRepository
import io.wwdaigo.domain.usecases.GetIssue
import io.wwdaigo.domain.usecases.ListIssues
import io.wwdaigo.domain.usecases.impl.GetIssueImpl
import io.wwdaigo.domain.usecases.impl.ListIssuesImpl

@Module
class UseCasesModule {

    @Provides
    fun provideGetIssue(repository: IssuesRepository): GetIssue {
        return GetIssueImpl(repository)
    }

    @Provides
    fun provideListIssues(repository: IssuesRepository): ListIssues {
        return ListIssuesImpl(repository)
    }
}