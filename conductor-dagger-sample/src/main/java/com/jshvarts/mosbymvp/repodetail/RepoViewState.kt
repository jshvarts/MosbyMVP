package com.jshvarts.mosbymvp.repodetail

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState
import com.jshvarts.mosbymvp.domain.GithubRepo

class RepoViewState : RestorableViewState<RepoDetailContract.View> {
    companion object {
        private val KEY_DATA = "com.jshvarts.mosbymvp.repodetail.RepoViewState_repoDetail"
        private val STATE_DO_NOTHING = 0
        private val STATE_SHOW_DATA = 1
        private val STATE_SHOW_LOADING = 2
    }

    private var state = STATE_DO_NOTHING

    private var data: GithubRepo? = null

    override fun saveInstanceState(out: Bundle) {
        out.putParcelable(KEY_DATA, data)
    }

    override fun restoreInstanceState(bundle: Bundle?): RestorableViewState<RepoDetailContract.View>? {
        if (bundle == null) {
            return null
        }

        data = bundle.getParcelable(KEY_DATA)
        return this
    }

    fun setData(data: GithubRepo) {
        state = STATE_SHOW_DATA
        this.data = data
    }

    fun setShowLoading() {
        state = STATE_SHOW_LOADING
    }

    override fun apply(view: RepoDetailContract.View, retained: Boolean) {
        when (state) {
            STATE_SHOW_DATA -> view.onLoadRepoDetailSuccess(data!!)
            STATE_SHOW_LOADING -> view.showLoading()
        }
    }
}