package com.jshvarts.mosbymvp.repodetail

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import com.jshvarts.mosbymvp.GithubApp
import com.jshvarts.mosbymvp.R
import com.jshvarts.mosbymvp.domain.GithubRepo
import com.jshvarts.mosbymvp.mvp.BaseViewController
import timber.log.Timber

class RepoDetailViewController : BaseViewController<RepoDetailContract.View, RepoDetailContract.Presenter, RepoViewState>(), RepoDetailContract.View {

    companion object {
        val LOGIN: String = "login"
        val REPO_NAME: String = "repoName"
    }

    @BindView(R.id.repo_name_textview)
    lateinit var repoNameTextView: TextView

    @BindView(R.id.loading_indicator)
    lateinit var loadingIndicator: ProgressBar

    override fun onNewViewStateInstance() {
        presenter.loadRepo(args.getString(LOGIN), args.getString(REPO_NAME))
    }

    override fun showLoading() {
        viewState.setShowLoading()
        repoNameTextView.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingIndicator.visibility = View.GONE
        repoNameTextView.visibility = View.VISIBLE
    }

    override fun onLoadRepoDetailSuccess(repoDetal: GithubRepo) {
        viewState.setData(repoDetal)
        repoNameTextView.visibility = View.VISIBLE
        repoNameTextView.text = "${repoDetal.name} has ${repoDetal.stars} stars"
    }

    override fun onLoadRepoError(throwable: Throwable) {
        viewState.setShowError()
        Timber.e(throwable)
        showMessage(R.string.error_loading_repo_detail)
    }

    override fun createViewState() = RepoViewState()

    override fun createPresenter() = DaggerRepoDetailComponent.builder()
            .appComponent(GithubApp.component)
            .repoDetailModule(RepoDetailModule())
            .build()
            .presenter()

    override fun getLayoutId() = R.layout.repo_detail

    override fun getToolbarTitleId() = R.string.repo_detail_title
}