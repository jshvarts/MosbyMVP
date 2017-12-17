package com.jshvarts.mosbymvp.data

import com.jshvarts.mosbymvp.domain.GithubRepo
import io.reactivex.Maybe
import io.reactivex.Single

class GithubDataStore(private val githubService: GithubService) {
    // TODO handle different http status codes including 404 to emit Maybe.empty()
    fun loadRepos(login: String): Maybe<List<GithubRepo>> = githubService.repos(login)

    fun loadRepo(login: String, repoName: String): Single<GithubRepo> = githubService.repo(login, repoName)
}