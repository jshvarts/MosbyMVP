package com.jshvarts.mosbymvp.data

import com.jshvarts.mosbymvp.domain.GithubRepo
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{login}/repos")
    fun repos(@Path("login") login: String): Maybe<List<GithubRepo>>

    @GET("repos/{login}/{repoName}")
    fun repo(@Path("login") login: String, @Path("repoName") repoName: String): Single<GithubRepo>
}