package ru.mrroot.gitclient67.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.mrroot.gitclient67.data.user.GitHubUserRepos

@Parcelize
data class GitHubUserReposViewModel(
    val name: String = "",
    val description: String = "",
    val language: String = "",
    val forks_count: String = ""
) : Parcelable {

    object Mapper {

        fun map(repos: GitHubUserRepos) =
            GitHubUserReposViewModel(
                repos.name.uppercase(),
                repos.description ?: "",
                repos.language ?: "",
                repos.forksCount ?: ""
            )

    }

}