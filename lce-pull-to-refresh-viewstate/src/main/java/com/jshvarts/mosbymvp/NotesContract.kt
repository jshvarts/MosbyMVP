package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView
import com.jshvarts.mosbymvp.domain.Note

interface NotesContract {
    interface View : MvpLceView<List<Note>> {
        fun onNoteClicked(note: Note)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData(pullToRefresh: Boolean)
    }
}