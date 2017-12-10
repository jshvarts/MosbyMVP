package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GreetingPresenter : MvpBasePresenter<GreetingContract.View>(), GreetingContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun loadHello() {
        disposables.add(GetGreetingUseCase.getHelloGreeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.hideLoading() }
                .subscribe({ view?.displayGreeting(it) }, { view?.displayError() }))
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        if (!retainInstance) {
            disposables.clear()
        }
    }
}