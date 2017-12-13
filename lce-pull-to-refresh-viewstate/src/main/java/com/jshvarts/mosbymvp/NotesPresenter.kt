package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.jshvarts.mosbymvp.domain.GetNotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotesPresenter : MvpBasePresenter<NotesContract.View>(), NotesContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun loadData(pullToRefresh: Boolean) {
        disposables.add(GetNotesUseCase.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.map { Note(it.id, it.noteText) } }
                .subscribe({
                    ifViewAttached { view ->
                        view.setData(it)
                        view.showContent()
                    }
                }, { error -> ifViewAttached { view -> view.showError(error, pullToRefresh) } }))
    }

    override fun destroy() {
        super.destroy()
        disposables.clear()
    }
}