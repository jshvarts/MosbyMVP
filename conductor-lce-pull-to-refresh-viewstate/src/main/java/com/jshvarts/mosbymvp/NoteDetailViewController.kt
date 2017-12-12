package com.jshvarts.mosbymvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Controller
import timber.log.Timber

class NoteDetailViewController : Controller() {

    companion object {
        val INTENT_NOTE_ID = "noteId"
    }

    @BindView(R.id.note_id_textview)
    lateinit var noteIdTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.note_detail_view, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        args.getInt(INTENT_NOTE_ID, 0).apply {
            Timber.d("noteId passed in " + this)
            noteIdTextView.text = this.toString()
        }
    }
}