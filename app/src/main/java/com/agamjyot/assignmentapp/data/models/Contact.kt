package com.agamjyot.assignmentapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val description: String,
    val name: String,
    val number: String,
    val pic: String
): Parcelable