package ru.mrroot.gitclientv6.data.user.datasource

import ru.mrroot.gitclientv6.App
import ru.mrroot.gitclientv6.data.storage.GitHubStorageFactory

object CacheUserDataSourceFactory {
    fun create(): CacheUserDataSource =
        CacheUserDataSourceImpl(GitHubStorageFactory.create(App.ContextHolder.context))
}