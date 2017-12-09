package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GreetingPresenter : MvpBasePresenter<GreetingContract.View>(), GreetingContract.Presenter {
    override fun loadHello() {
        GetGreetingUseCase.getHelloGreeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { if(isViewAttached) view.showLoading() }
                .doFinally { if(isViewAttached) view.hideLoading() }
                .subscribe({ if(isViewAttached) view.displayGreeting(it) }, { if(isViewAttached) view.displayError() })
    }

    override fun loadGoodbye() {
        GetGreetingUseCase.getGoodbyeGreeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { if(isViewAttached) view.showLoading() }
                .doFinally { if(isViewAttached) view.hideLoading() }
                .subscribe({ if(isViewAttached) view.displayGreeting(it) }, { if(isViewAttached) view.displayError() })
    }
}