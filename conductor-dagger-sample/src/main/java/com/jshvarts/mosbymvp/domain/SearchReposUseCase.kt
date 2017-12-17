package com.jshvarts.mosbymvp.domain

import io.reactivex.Maybe

class SearchReposUseCase(private val githubDataStore: GitbhubDataStore) {

    fun search(owner: String): Maybe<List<GithubRepo>> {
        return githubDataStore.loadRepos(owner)
    }
}