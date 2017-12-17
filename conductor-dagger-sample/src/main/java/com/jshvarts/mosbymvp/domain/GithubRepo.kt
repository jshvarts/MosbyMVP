package com.jshvarts.mosbymvp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepo(val repoName: String) : Parcelable