package com.jshvarts.mosbymvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val notes = mutableListOf<Note>()

    var onItemClick: (Note) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteText = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false) as TextView
        val viewHolder = ViewHolder(noteText)
        noteText.setOnClickListener { onItemClick(notes[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        notes[position].apply { holder.noteText.text = this.noteText }
    }

    override fun getItemCount() = notes.size

    fun updateNotes(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    fun getNotesList() = notes

    class ViewHolder(val noteText: TextView) : RecyclerView.ViewHolder(noteText)
}