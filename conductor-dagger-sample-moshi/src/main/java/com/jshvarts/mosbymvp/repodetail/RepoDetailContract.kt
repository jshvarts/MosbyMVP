package com.jshvarts.mosbymvp.repodetail

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.jshvarts.mosbymvp.domain.GithubRepo

interface RepoDetailContract {
    interface View : MvpView {
        fun onLoadRepoDetailSuccess(repoDetal: GithubRepo)
        fun onLoadRepoError(throwable: Throwable)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : MvpPresenter<View> {
        fun loadRepo(login: String, repoName: String)
    }
}