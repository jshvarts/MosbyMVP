package com.jshvarts.mosbymvp.searchrepos

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.jshvarts.mosbymvp.domain.GithubRepo

interface SearchContract {
    interface View : MvpView {
        fun onSearchAction(code: Int): Boolean
        fun onSearchSuccess(repos: List<GithubRepo>)
        fun onSearchError(throwable: Throwable)
        fun onRepoClicked(repo: GithubRepo)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : MvpPresenter<View> {
        fun searchRepos(username: String)
    }
}