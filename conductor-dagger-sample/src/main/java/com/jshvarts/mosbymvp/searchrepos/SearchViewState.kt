package com.jshvarts.mosbymvp.searchrepos

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState
import com.jshvarts.mosbymvp.domain.GithubRepo

class SearchViewState : RestorableViewState<SearchContract.View> {
    companion object {
        private const val KEY_DATA = "com.jshvarts.mosbymvp.searchrepos.SearchViewState_searchResults"
        private const val STATE_DO_NOTHING = 0
        private const val STATE_SHOW_DATA = 1
        private const val STATE_SHOW_LOADING = 2
        private const val STATE_SHOW_ERROR = 3
    }

    private var state = STATE_DO_NOTHING

    private var data: ArrayList<GithubRepo>? = null

    override fun saveInstanceState(out: Bundle) {
        out.putParcelableArrayList(KEY_DATA, data)
    }

    override fun restoreInstanceState(bundle: Bundle?): RestorableViewState<SearchContract.View>? {
        if (bundle == null) {
            return null
        }

        data = bundle.getParcelableArrayList(KEY_DATA)
        return this
    }

    fun setData(data: ArrayList<GithubRepo>) {
        state = STATE_SHOW_DATA
        this.data = data
    }

    fun setShowLoading() {
        state = STATE_SHOW_LOADING
    }

    fun setShowError() {
        state = STATE_SHOW_ERROR
    }

    override fun apply(view: SearchContract.View, retained: Boolean) {
        when (state) {
            STATE_SHOW_DATA -> view.onSearchSuccess(data!!)
            STATE_SHOW_LOADING -> view.showLoading()
            STATE_SHOW_ERROR -> data = null
        }
    }
}