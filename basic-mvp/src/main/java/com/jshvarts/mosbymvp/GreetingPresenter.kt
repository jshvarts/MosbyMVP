package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GreetingPresenter : MvpBasePresenter<GreetingContract.View>(), GreetingContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun loadHello() {
        disposables.add(GetGreetingUseCase.getHelloGreeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { if(isViewAttached) view.showLoading() }
                .doFinally { if(isViewAttached) view.hideLoading() }
                .subscribe({ if(isViewAttached) view.displayGreeting(it) }, { if(isViewAttached) view.displayError() }))
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        Timber.d("In detachView " + retainInstance)
        if (!retainInstance) {
            Timber.d("Clearing disposables")
            disposables.clear()
        }
    }
}