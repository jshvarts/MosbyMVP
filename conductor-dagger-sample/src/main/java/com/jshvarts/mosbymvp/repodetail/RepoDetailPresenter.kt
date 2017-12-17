package com.jshvarts.mosbymvp.repodetail

import com.jshvarts.mosbymvp.domain.RepoDetailUseCase
import com.jshvarts.mosbymvp.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoDetailPresenter(private val repoDetailUseCase: RepoDetailUseCase) : BasePresenter<RepoDetailContract.View>(), RepoDetailContract.Presenter {
    override fun loadRepo(login: String, repoName: String) {
        disposables.add(repoDetailUseCase.loadRepoDetail(login, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { ifViewAttached { view -> view.showLoading() } }
            .doFinally { ifViewAttached { view -> view.hideLoading() } }
            .subscribe({ ifViewAttached { view -> view.onLoadRepoDetailSuccess(it) } }, { ifViewAttached { view -> view.onLoadRepoError(it) } }))
    }
}