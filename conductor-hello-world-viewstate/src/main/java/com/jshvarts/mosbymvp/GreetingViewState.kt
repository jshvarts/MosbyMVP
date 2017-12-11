package com.jshvarts.mosbymvp

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState

class GreetingViewState : RestorableViewState<GreetingContract.View> {
    companion object {
        private val KEY_DATA = "com.jshvarts.mosbymvp.GreetingViewState_greetingData"
        private val STATE_DO_NOTHING = 0
        private val STATE_SHOW_DATA = 1
        private val STATE_SHOW_LOADING = 2
    }

    private var state = STATE_DO_NOTHING

    private var data: String? = null

    override fun saveInstanceState(out: Bundle) {
        out.putString(KEY_DATA, data)
    }

    override fun restoreInstanceState(bundle: Bundle?): RestorableViewState<GreetingContract.View>? {
        if (bundle == null) {
            return null
        }

        data = bundle.getString(KEY_DATA)
        return this
    }

    fun setData(data: String) {
        state = STATE_SHOW_DATA
        this.data = data
    }

    fun setShowLoading() {
        state = STATE_SHOW_LOADING
    }

    override fun apply(view: GreetingContract.View, retained: Boolean) {
        when (state) {
            STATE_SHOW_DATA -> view.showGreeting(data!!)
            STATE_SHOW_LOADING -> view.showLoading()
        }
    }
}