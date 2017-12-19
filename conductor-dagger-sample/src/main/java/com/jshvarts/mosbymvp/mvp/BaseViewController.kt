package com.jshvarts.mosbymvp.mvp

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState

abstract class BaseViewController<V : MvpView, P : MvpPresenter<V>, VS : ViewState<V>> : MvpViewStateController<V, P, VS>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(getLayoutId(), container, false)
        ButterKnife.bind(this, view)
        setToolbarTitle()
        return view
    }

    abstract override fun createViewState(): VS

    abstract override fun createPresenter(): P

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {
        //no-op
    }

    override fun onNewViewStateInstance() {
        //no-op
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @StringRes
    protected abstract fun getToolbarTitleId(): Int

    private fun setToolbarTitle() {
        // if the Activity happens to be non-AppCompatActivity or it does not have ActionBar, simply do not set the title
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = resources?.getString(getToolbarTitleId())
            setDisplayHomeAsUpEnabled(router.backstackSize > 1)
        }
    }

    protected fun View.hideKeyboard() {
        val inputMethodManager = applicationContext?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }

    protected fun showMessage(@StringRes msgResId: Int) {
        Toast.makeText(this.applicationContext, msgResId, Toast.LENGTH_SHORT).show()
    }
}