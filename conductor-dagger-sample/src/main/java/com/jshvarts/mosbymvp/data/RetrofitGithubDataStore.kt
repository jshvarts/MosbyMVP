package com.jshvarts.mosbymvp.data

import com.jshvarts.mosbymvp.domain.GitbhubDataStore
import com.jshvarts.mosbymvp.domain.GithubRepo
import io.reactivex.Single

class RetrofitGithubDataStore(private val githubService: RetrofitGithubService) : GitbhubDataStore {
    override fun loadRepos(login: String): Single<List<GithubRepo>> = githubService.repos(login)
    override fun loadRepo(login: String, repoName: String): Single<GithubRepo> = githubService.repo(login, repoName)
}