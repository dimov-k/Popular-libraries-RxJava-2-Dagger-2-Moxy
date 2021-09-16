package ru.mrroot.popularlibraryuser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val userId: Int,
    val login: String
) : Parcelable