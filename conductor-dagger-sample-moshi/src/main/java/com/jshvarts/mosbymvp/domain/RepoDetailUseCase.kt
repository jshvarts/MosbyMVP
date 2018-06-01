package com.jshvarts.mosbymvp.domain

import io.reactivex.Single

class RepoDetailUseCase(private val githubDataStore: GitbhubDataStore) {

    fun loadRepoDetail(login: String, repoName: String): Single<GithubRepo> {
        return githubDataStore.loadRepo(login, repoName)
    }
}