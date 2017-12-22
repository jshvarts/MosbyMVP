package com.jshvarts.mosbymvp.domain

import io.reactivex.Single

class SearchReposUseCase(private val githubDataStore: GitbhubDataStore) {

    fun search(owner: String): Single<List<GithubRepo>> {
        return githubDataStore.loadRepos(owner)
    }
}