package ru.mrroot.gitclient67.data.di.modules

import dagger.Binds
import dagger.Module
import ru.mrroot.gitclient67.data.user.GitHubUserRepository
import ru.mrroot.gitclient67.data.user.GitHubUserRepositoryImpl
import ru.mrroot.gitclient67.data.user.datasource.CacheUserDataSource
import ru.mrroot.gitclient67.data.user.datasource.CacheUserDataSourceImpl
import ru.mrroot.gitclient67.data.user.datasource.CloudUserDataSource
import ru.mrroot.gitclient67.data.user.datasource.UserDataSource
import javax.inject.Singleton

@Module
interface UsersModule {
    @Singleton
    @Binds
    fun bindGitHubUserRepository(repository: GitHubUserRepositoryImpl): GitHubUserRepository

    @Singleton
    @Binds
    fun bindUserDataSource(dataSource: CloudUserDataSource): UserDataSource

    @Singleton
    @Binds
    fun bindCacheUserDataSource(dataSource: CacheUserDataSourceImpl): CacheUserDataSource
}