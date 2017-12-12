package com.jshvarts.mosbymvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import timber.log.Timber

class NoteDetailActivity : AppCompatActivity() {

    @BindView(R.id.note_id_textview)
    lateinit var noteIdTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        ButterKnife.bind(this)

        intent.getIntExtra(INTENT_NOTE_ID, 0).apply {
            Timber.d("noteId passed in " + this)
            noteIdTextView.text = this.toString()
        }
    }

    companion object {

        private val INTENT_NOTE_ID = "noteId"

        fun newIntent(context: Context, note: Note): Intent {
            val intent = Intent(context, NoteDetailActivity::class.java)
            intent.putExtra(INTENT_NOTE_ID, note.id)
            return intent
        }
    }
}