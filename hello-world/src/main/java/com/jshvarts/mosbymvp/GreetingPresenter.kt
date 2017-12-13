package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.jshvarts.mosbymvp.domain.GetGreetingUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GreetingPresenter : MvpBasePresenter<GreetingContract.View>(), GreetingContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun loadGreeting() {
        disposables.add(GetGreetingUseCase.getHelloGreeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { ifViewAttached { view -> view.showLoading() } }
                .doFinally { ifViewAttached { view -> view.hideLoading() } }
                .subscribe({ ifViewAttached { view -> view.showGreeting(it) } }, { ifViewAttached { view -> view.hideLoading() } }))
    }

    override fun destroy() {
        super.destroy()
        disposables.clear()
    }
}