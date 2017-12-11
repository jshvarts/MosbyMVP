package com.jshvarts.mosbymvp

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.ParcelableListLceViewState
import com.jshvarts.mosbymvp.domain.Note

class MainActivity : MvpLceViewStateActivity<SwipeRefreshLayout, List<Note>, NotesContract.View, NotesPresenter>(),
        NotesContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    private lateinit var recyclerViewAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        // set up contentView == SwipeRefreshView
        contentView.setOnRefreshListener(this)

        initRecyclerView(this)
    }

    override fun createPresenter() = NotesPresenter()

    override fun createViewState(): LceViewState<List<Note>, NotesContract.View> {
        return ParcelableListLceViewState<List<Note>, NotesContract.View>()
    }

    override fun getErrorMessage(e: Throwable, pullToRefresh: Boolean)
            = NoteErrorMessage(e, pullToRefresh, this).getMessage()

    /**
     * Creates the view state object
     */
    override fun getData(): List<Note>? {
        return recyclerViewAdapter?.getNotesList()
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadData(pullToRefresh)
    }

    override fun setData(data: List<Note>) {
        recyclerViewAdapter.updateNotes(data)
    }

    override fun onRefresh() = loadData(true)

    override fun showContent() {
        super.showContent()
        contentView.isRefreshing = false
    }

    override fun showError(e: Throwable?, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        contentView.isRefreshing = false
    }

    override fun showLoading(pullToRefresh: Boolean) {
        super.showLoading(pullToRefresh)
        if (pullToRefresh && !contentView.isRefreshing) {
            contentView.isRefreshing = true
        }
    }

    override fun onNoteClicked(note: Note) {
        val intent = NoteDetailActivity.newIntent(this, note)
        startActivity(intent)
    }

    private fun initRecyclerView(context: Context) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = NotesAdapter()
        recyclerViewAdapter.onItemClick = { onNoteClicked(it)}
        recyclerView.adapter = recyclerViewAdapter
    }
}
