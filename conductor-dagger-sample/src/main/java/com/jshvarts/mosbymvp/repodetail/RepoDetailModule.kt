package com.jshvarts.mosbymvp.repodetail

import com.jshvarts.mosbymvp.data.GithubDataStore
import com.jshvarts.mosbymvp.di.PerScreen
import com.jshvarts.mosbymvp.domain.RepoDetailUseCase
import dagger.Module
import dagger.Provides

@Module
class RepoDetailModule {
    @PerScreen
    @Provides
    fun provideRepoDetailUseCase(githubDataStore: GithubDataStore) = RepoDetailUseCase(githubDataStore)

    @PerScreen
    @Provides
    fun providePresenter(repoDetailUseCase: RepoDetailUseCase) = RepoDetailPresenter(repoDetailUseCase)
}