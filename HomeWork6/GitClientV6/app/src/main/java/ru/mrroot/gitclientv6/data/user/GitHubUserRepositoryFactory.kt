package ru.mrroot.gitclientv6.data.user

import ru.mrroot.gitclientv6.data.user.datasource.CacheUserDataSourceFactory
import ru.mrroot.gitclientv6.data.user.datasource.UserDataSourceFactory

object GitHubUserRepositoryFactory {
    private val repository: GitHubUserRepository by lazy {
        GitHubUserRepositoryImpl(
            UserDataSourceFactory.create(),
            CacheUserDataSourceFactory.create()
        )
    }

    fun create(): GitHubUserRepository = repository
}