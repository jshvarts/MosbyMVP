package com.jshvarts.mosbymvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController

class GreetingViewController : MvpViewStateController<GreetingContract.View, GreetingContract.Presenter, GreetingViewState>(), GreetingContract.View {

    @BindView(R.id.greeting_textview)
    lateinit var greetingTextView: TextView

    @BindView(R.id.loading_indicator)
    lateinit var loadingIndicator: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.greeting_view, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun createPresenter(): GreetingPresenter = GreetingPresenter()

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {
        //no-op
    }

    override fun onNewViewStateInstance() {
        //no-op
    }

    override fun createViewState(): GreetingViewState = GreetingViewState()

    @OnClick(R.id.hello_greeting_button)
    override fun onGreetingButtonClicked() {
        presenter.loadGreeting()
    }

    override fun showGreeting(greetingText: String) {
        viewState.setData(greetingText)
        greetingTextView.visibility = View.VISIBLE
        greetingTextView.text = greetingText
    }

    override fun showLoading() {
        viewState.setShowLoading()
        greetingTextView.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingIndicator.visibility = View.GONE
    }

    override fun showError() {
        viewState.setShowError()
        Toast.makeText(applicationContext, applicationContext?.getString(R.string.greeting_loading_error), Toast.LENGTH_LONG).show()
    }
}