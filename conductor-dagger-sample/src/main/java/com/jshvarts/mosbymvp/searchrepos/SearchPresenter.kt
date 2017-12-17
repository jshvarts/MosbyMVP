package com.jshvarts.mosbymvp.searchrepos

import com.jshvarts.mosbymvp.domain.SearchReposUseCase
import com.jshvarts.mosbymvp.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val searchReposUseCase: SearchReposUseCase) : BasePresenter<SearchContract.View>(), SearchContract.Presenter {
    override fun searchRepos(username: String) {
        disposables.add(searchReposUseCase.search(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { ifViewAttached { view -> view.showLoading() } }
            .doFinally { ifViewAttached { view -> view.hideLoading() } }
            .subscribe({ ifViewAttached { view -> view.onSearchSuccess(it) } }, { ifViewAttached { view -> view.onSearchError(it) } }))
    }
}