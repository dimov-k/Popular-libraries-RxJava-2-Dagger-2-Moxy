package ru.mrroot.gitclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepository(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val description: String? = null,
    @Expose val forks: Int? = null,
    @Expose val createdAt: String? = null,
    @Expose val updatedAt: String? = null,
    @Expose val pushedAt: String? = null,
    @Expose val language: String? = null,
    @Expose val watchers: String? = null,
    @Expose val openIssuesCount: String? = null
) : Parcelable