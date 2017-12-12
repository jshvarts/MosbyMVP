package com.jshvarts.mosbymvp

import android.content.Context

class NoteErrorMessage(private val t: Throwable, private val pullToRefresh: Boolean, private val context: Context) {
    fun getMessage(): String {
        // TODO distinguish type of exception and return different strings
        return if (pullToRefresh) {
            context.getString(R.string.error_loading)
        } else {
            context.getString(R.string.error_loading_retry)
        }
    }
}