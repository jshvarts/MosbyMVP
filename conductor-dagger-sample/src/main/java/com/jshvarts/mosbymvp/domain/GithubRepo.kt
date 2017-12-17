package com.jshvarts.mosbymvp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepo(
        val name: String,
        val owner: Owner,
        @SerializedName("stargazers_count") val stars: Int) : Parcelable