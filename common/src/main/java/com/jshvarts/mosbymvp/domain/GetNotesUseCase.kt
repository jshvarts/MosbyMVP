package com.jshvarts.mosbymvp.domain

import com.jshvarts.mosbymvp.data.NotesRepository
import io.reactivex.Single

/**
 * In a Production app, inject the Use Case in your Presentation layer.
 */
object GetNotesUseCase {
    fun getNotes(): Single<List<NoteEntity>> = NotesRepository.getNotes()
}