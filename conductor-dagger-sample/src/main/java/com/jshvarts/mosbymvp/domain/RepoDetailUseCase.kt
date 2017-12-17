package com.jshvarts.mosbymvp.domain

import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RepoDetailUseCase {

    fun loadRepoDetail(repoName: String): Single<GithubRepo> {
        return Single.just(GithubRepo(repoName))
                .delay(2, TimeUnit.SECONDS)
    }
}