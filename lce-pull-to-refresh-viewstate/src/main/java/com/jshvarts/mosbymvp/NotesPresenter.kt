package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotesPresenter : MvpBasePresenter<NotesContract.View>(), NotesContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun loadData(pullToRefresh: Boolean) {
        disposables.add(GetNotesUseCase.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.setData(it)
                    view?.showContent()
                }, { view?.showError(it, pullToRefresh) }))
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        if (!retainInstance) {
            disposables.clear()
        }
    }
}