package ru.mrroot.gitclientv6.data.user.datasource

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.mrroot.gitclientv6.data.storage.GitHubStorage
import ru.mrroot.gitclientv6.data.user.GitHubUser
import ru.mrroot.gitclientv6.data.user.GitHubUserRepos

class CacheUserDataSourceImpl(private val gitHubStorage: GitHubStorage) : CacheUserDataSource {

    override fun getUsers(): Single<List<GitHubUser>> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUsers()

    override fun getUserByLogin(userId: String): Maybe<GitHubUser> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUserByLogin(userId)
            .toMaybe()

    override fun retain(users: List<GitHubUser>): Single<List<GitHubUser>> =
        gitHubStorage
            .gitHubUserDao()
            .retain(users)
            .andThen(getUsers())

    override fun retain(user: GitHubUser): Single<GitHubUser> =
        Single.fromCallable { user }

    override fun getUserRepos(reposUrl: String): Single<List<GitHubUserRepos>> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUserRepos(reposUrl)

    override fun retainRepos(
        userRepos: List<GitHubUserRepos>,
        reposUrl: String
    ): Single<List<GitHubUserRepos>> =
        gitHubStorage
            .gitHubUserDao()
            .retainRepos(userRepos)
            .andThen(getUserRepos(reposUrl))
}