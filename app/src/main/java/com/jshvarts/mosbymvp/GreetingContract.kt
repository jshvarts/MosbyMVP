package com.jshvarts.mosbymvp

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface GreetingContract {
    interface View : MvpView {
        fun onHelloButtonClicked()
        fun onGoodbyeButtonClicked()
        fun showLoading()
        fun hideLoading()
        fun displayGreeting(greetingText: String)
        fun displayError()
    }

    interface Presenter : MvpPresenter<View> {
        fun loadHello()
        fun loadGoodbye()
    }
}