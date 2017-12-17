package com.jshvarts.mosbymvp.data

import com.jshvarts.mosbymvp.domain.GitbhubDataStore
import com.jshvarts.mosbymvp.domain.GithubRepo
import io.reactivex.Maybe
import io.reactivex.Single

class RetrofitGithubDataStore(private val githubService: RetrofitGithubService) : GitbhubDataStore {
    // TODO handle different http status codes including 404 to emit Maybe.empty()
    override fun loadRepos(login: String): Maybe<List<GithubRepo>> = githubService.repos(login)

    override fun loadRepo(login: String, repoName: String): Single<GithubRepo> = githubService.repo(login, repoName)
}