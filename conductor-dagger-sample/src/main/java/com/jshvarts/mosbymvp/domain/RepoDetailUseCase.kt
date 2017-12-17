package com.jshvarts.mosbymvp.domain

import com.jshvarts.mosbymvp.data.GithubDataStore
import io.reactivex.Single

class RepoDetailUseCase(private val githubDataStore: GithubDataStore) {

    fun loadRepoDetail(login: String, repoName: String): Single<GithubRepo> {
        return githubDataStore.loadRepo(login, repoName)
    }
}