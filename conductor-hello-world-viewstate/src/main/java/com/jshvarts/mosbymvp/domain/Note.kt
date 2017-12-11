package com.jshvarts.mosbymvp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(val id: Int, val noteText: String) : Parcelable