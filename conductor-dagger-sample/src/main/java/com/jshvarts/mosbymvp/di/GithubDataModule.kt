package com.jshvarts.mosbymvp.di

import com.jshvarts.mosbymvp.data.RetrofitGithubDataStore
import com.jshvarts.mosbymvp.data.RetrofitGithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GithubDataModule {

    @Singleton
    @Provides
    fun provideGithubService(): RetrofitGithubService =
            Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitGithubService::class.java)

    @Singleton
    @Provides
    fun provideDataStore(githubService: RetrofitGithubService) = RetrofitGithubDataStore(githubService)
}