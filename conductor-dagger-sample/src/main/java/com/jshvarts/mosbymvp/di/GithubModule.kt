package com.jshvarts.mosbymvp.di

import com.jshvarts.mosbymvp.data.RetrofitGithubDataStore
import com.jshvarts.mosbymvp.data.RetrofitGithubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class GithubModule {

    @Provides
    fun provideGithubService(): RetrofitGithubService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

        val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        return Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitGithubService::class.java)
    }

    @Provides
    fun provideDataStore(githubService: RetrofitGithubService) = RetrofitGithubDataStore(githubService)
}