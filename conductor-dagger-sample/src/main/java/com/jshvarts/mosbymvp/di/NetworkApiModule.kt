package com.jshvarts.mosbymvp.di

import com.jshvarts.mosbymvp.data.GithubDataStore
import com.jshvarts.mosbymvp.data.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkApiModule {

    @Singleton
    @Provides
    fun provideGithubService(): GithubService =
            Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GithubService::class.java)

    @Singleton
    @Provides
    fun provideDataStore(githubService: GithubService) = GithubDataStore(githubService)
}