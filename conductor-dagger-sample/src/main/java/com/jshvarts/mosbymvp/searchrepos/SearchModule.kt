package com.jshvarts.mosbymvp.searchrepos

import com.jshvarts.mosbymvp.data.GithubDataStore
import com.jshvarts.mosbymvp.di.PerScreen
import com.jshvarts.mosbymvp.domain.SearchReposUseCase
import dagger.Module
import dagger.Provides

@Module
class SearchModule {
    @PerScreen
    @Provides
    fun provideSearchUseCase(githubDataStore: GithubDataStore) = SearchReposUseCase(githubDataStore)

    @PerScreen
    @Provides
    fun providePresenter(searchReposUseCase: SearchReposUseCase) = SearchPresenter(searchReposUseCase)
}