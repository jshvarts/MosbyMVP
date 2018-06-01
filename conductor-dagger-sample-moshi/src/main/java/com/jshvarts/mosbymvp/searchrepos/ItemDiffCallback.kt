package com.jshvarts.mosbymvp.searchrepos

import android.support.v7.util.DiffUtil
import com.jshvarts.mosbymvp.domain.GithubRepo

class ItemDiffCallback(private val old: List<GithubRepo>,
                       private val new: List<GithubRepo>) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex].name == new[newIndex].name
    }

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex] == new[newIndex]
    }
}