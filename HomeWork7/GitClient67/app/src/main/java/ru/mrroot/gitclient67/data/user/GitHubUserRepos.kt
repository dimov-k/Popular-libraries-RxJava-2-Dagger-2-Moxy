package ru.mrroot.gitclient67.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github_repos")
data class GitHubUserRepos(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    val language: String?,
    @ColumnInfo(name = "forks")
    @SerializedName("forks_count")
    val forksCount: String?,
    @ColumnInfo(name = "repos")
    var reposUrl: String?,
    @ColumnInfo(name = "migrate")
    val migrate: String?
)
