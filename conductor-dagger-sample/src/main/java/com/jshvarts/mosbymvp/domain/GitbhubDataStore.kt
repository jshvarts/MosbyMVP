package com.jshvarts.mosbymvp.domain

import io.reactivex.Maybe
import io.reactivex.Single

interface GitbhubDataStore {
    fun loadRepos(login: String): Maybe<List<GithubRepo>>
    fun loadRepo(login: String, repoName: String): Single<GithubRepo>
}