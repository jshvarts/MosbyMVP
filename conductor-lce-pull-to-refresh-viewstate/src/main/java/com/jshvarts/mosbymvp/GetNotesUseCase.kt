package com.jshvarts.mosbymvp

import io.reactivex.Single
import java.util.Random
import java.util.concurrent.TimeUnit

object GetNotesUseCase {

    fun getNotes(): Single<List<Note>> = Single.just(generateNotes()).delay(2, TimeUnit.SECONDS)

    /**
     * Generates a list of note items randomly between 2 and 50
     */
    private fun generateNotes() = (1..(2..50).random()).map { Note(it, "note $it") }

    private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start
}