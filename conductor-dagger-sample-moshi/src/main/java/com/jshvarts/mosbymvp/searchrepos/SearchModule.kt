package com.jshvarts.mosbymvp.searchrepos

import com.jshvarts.mosbymvp.data.RetrofitGithubDataStore
import com.jshvarts.mosbymvp.di.PerScreen
import com.jshvarts.mosbymvp.domain.SearchReposUseCase
import dagger.Module
import dagger.Provides

@Module
class SearchModule {
    @PerScreen
    @Provides
    fun provideSearchUseCase(githubDataStore: RetrofitGithubDataStore) = SearchReposUseCase(githubDataStore)
}