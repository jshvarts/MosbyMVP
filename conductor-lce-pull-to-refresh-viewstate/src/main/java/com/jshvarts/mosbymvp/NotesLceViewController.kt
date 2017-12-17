package com.jshvarts.mosbymvp

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.hannesdorfmann.mosby3.conductor.viewstate.lce.MvpLceViewStateController
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.ParcelableListLceViewState

class NotesLceViewController : MvpLceViewStateController<SwipeRefreshLayout, List<Note>, NotesContract.View,
        NotesContract.Presenter>(), NotesContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    private lateinit var recyclerViewAdapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.notes_view, container, false)
        ButterKnife.bind(this, view)

        initRecyclerView(view.context)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

        // set up contentView == SwipeRefreshView
        contentView.setOnRefreshListener(this)
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadData(pullToRefresh)
    }

    override fun createPresenter(): NotesContract.Presenter = NotesPresenter()

    override fun setData(data: List<Note>) {
        recyclerViewAdapter.updateNotes(data)
    }

    override fun getData(): List<Note> {
        return recyclerViewAdapter.getNotesList()
    }

    override fun showContent() {
        super.showContent()
        contentView.isRefreshing = false
    }

    override fun getErrorMessage(e: Throwable, pullToRefresh: Boolean)
            = NoteErrorMessage(e, pullToRefresh, view!!.context).getMessage()

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

    override fun createViewState(): LceViewState<List<Note>, NotesContract.View> {
        return ParcelableListLceViewState<List<Note>, NotesContract.View>()
    }

    override fun onRefresh() {
        loadData(true)
    }

    override fun onNoteClicked(note: Note) {
        val noteDetailView = NoteDetailViewController().apply {
            args.putInt(NoteDetailViewController.INTENT_NOTE_ID, note.id)
        }
        router.pushController(RouterTransaction.with(noteDetailView)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun initRecyclerView(context: Context) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = NotesAdapter()
        recyclerViewAdapter.onItemClick = { onNoteClicked(it)}
        recyclerView.adapter = recyclerViewAdapter
    }
}