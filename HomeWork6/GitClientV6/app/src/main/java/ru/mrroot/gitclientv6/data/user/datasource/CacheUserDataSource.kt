package ru.mrroot.gitclientv6.data.user.datasource


import io.reactivex.rxjava3.core.Single
import ru.mrroot.gitclientv6.data.user.GitHubUser
import ru.mrroot.gitclientv6.data.user.GitHubUserRepos

interface CacheUserDataSource : UserDataSource {

    fun retain(users: List<GitHubUser>): Single<List<GitHubUser>>
    fun retain(user: GitHubUser): Single<GitHubUser>
    fun retainRepos(
        userRepos: List<GitHubUserRepos>,
        reposUrl: String
    ): Single<List<GitHubUserRepos>>
}