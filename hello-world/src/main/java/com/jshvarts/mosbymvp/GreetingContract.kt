package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface GreetingContract {
    interface View : MvpView {
        fun onGreetingButtonClicked()
        fun showLoading()
        fun hideLoading()
        fun showGreeting(greetingText: String)
        fun showError()
    }

    interface Presenter : MvpPresenter<View> {
        fun loadGreeting()
    }
}