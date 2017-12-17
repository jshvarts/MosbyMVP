package com.jshvarts.mosbymvp.domain

import com.jshvarts.mosbymvp.data.GithubDataStore
import io.reactivex.Maybe

class SearchReposUseCase(private val githubDataStore: GithubDataStore) {

    fun search(owner: String): Maybe<List<GithubRepo>> {
        return githubDataStore.loadRepos(owner)
    }
}