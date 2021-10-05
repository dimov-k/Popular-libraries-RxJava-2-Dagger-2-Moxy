package ru.mrroot.gitclientv6.data.user.datasource

import ru.mrroot.gitclientv6.data.api.GitHubApiFactory

object UserDataSourceFactory {
    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())
}