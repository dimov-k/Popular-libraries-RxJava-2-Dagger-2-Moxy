package ru.mrroot.gitclientv6.presentation

import ru.mrroot.gitclientv6.data.user.GitHubUserRepos

data class GitHubUserReposViewModel(
    val name: String = "",
    val description: String = "",
    val language: String = "",
    val forks_count: String = ""
) {

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