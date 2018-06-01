package com.jshvarts.mosbymvp.mvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : MvpView> : MvpBasePresenter<V>() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    override fun destroy() {
        disposables.clear()
    }
}