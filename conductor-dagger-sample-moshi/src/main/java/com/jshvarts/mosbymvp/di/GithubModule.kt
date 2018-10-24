package com.jshvarts.mosbymvp.di

import android.app.Application
import com.jshvarts.mosbymvp.data.RetrofitGithubDataStore
import com.jshvarts.mosbymvp.data.RetrofitGithubService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@Module
class GithubModule {

    @Provides
    fun provideGithubService(cache: Cache, cacheInterceptor: Interceptor): RetrofitGithubService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

        val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache)
                .build()

        return Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RetrofitGithubService::class.java)
    }

    @Provides
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024L // 10 MB
        return Cache(application.applicationContext.cacheDir, cacheSize)
    }

    @Provides
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                    .maxAge(2, TimeUnit.MINUTES)
                    .build()
            originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
        }
    }

    @Provides
    fun provideDataStore(githubService: RetrofitGithubService) = RetrofitGithubDataStore(githubService)
}