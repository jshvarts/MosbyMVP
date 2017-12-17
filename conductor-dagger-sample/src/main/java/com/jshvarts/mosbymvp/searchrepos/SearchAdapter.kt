package com.jshvarts.mosbymvp.searchrepos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jshvarts.mosbymvp.R
import com.jshvarts.mosbymvp.domain.GithubRepo

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val repos = mutableListOf<GithubRepo>()

    var onItemClick: (GithubRepo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val repoName = LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_list_item, parent, false) as TextView
        val viewHolder = ViewHolder(repoName)
        repoName.setOnClickListener { onItemClick(repos[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        repos[position].apply { holder.repoName.text = this.name }
    }

    override fun getItemCount() = repos.size

    fun updateRepos(notes: List<GithubRepo>) {
        this.repos.clear()
        this.repos.addAll(notes)
        notifyDataSetChanged()
    }

    class ViewHolder(val repoName: TextView) : RecyclerView.ViewHolder(repoName)
}