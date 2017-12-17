package com.jshvarts.mosbymvp.repodetail

import com.jshvarts.mosbymvp.di.PerScreen
import com.jshvarts.mosbymvp.domain.RepoDetailUseCase
import dagger.Module
import dagger.Provides

@Module
class RepoDetailModule {
    @PerScreen
    @Provides
    fun provideRepoDetailUseCase() = RepoDetailUseCase()

    @PerScreen
    @Provides
    fun providePresenter(repoDetailUseCase: RepoDetailUseCase) = RepoDetailPresenter(repoDetailUseCase)
}