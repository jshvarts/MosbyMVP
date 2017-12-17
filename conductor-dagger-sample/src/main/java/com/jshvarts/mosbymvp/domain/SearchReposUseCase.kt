package com.jshvarts.mosbymvp.domain

import io.reactivex.Maybe
import java.util.concurrent.TimeUnit

class SearchReposUseCase {

    fun search(username: String): Maybe<List<GithubRepo>> {
        return Maybe.just(listOf(GithubRepo("repo1"), GithubRepo("repo2"), GithubRepo("repo3")))
                .delay(2, TimeUnit.SECONDS)
    }
}