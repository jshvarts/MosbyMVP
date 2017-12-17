package com.jshvarts.mosbymvp.repodetail

import com.jshvarts.mosbymvp.domain.RepoDetailUseCase
import com.jshvarts.mosbymvp.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoDetailPresenter @Inject constructor(private val repoDetailUseCase: RepoDetailUseCase) : BasePresenter<RepoDetailContract.View>(), RepoDetailContract.Presenter {
    override fun loadRepo(repoName: String) {
        disposables.add(repoDetailUseCase.loadRepoDetail(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { ifViewAttached { view -> view.showLoading() } }
            .doFinally { ifViewAttached { view -> view.hideLoading() } }
            .subscribe({ ifViewAttached { view -> view.onLoadRepoDetailSuccess(it) } }, { ifViewAttached { view -> view.onLoadRepoError(it) } }))
    }
}