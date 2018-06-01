package com.jshvarts.mosbymvp.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class GithubRepo(
        val name: String,
        val owner: Owner,
        @Json(name = "stargazers_count") val stars: Int) : Parcelable