package ru.mrroot.gitclientv6.data.user.datasource


import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.mrroot.gitclientv6.data.api.GitHubApi
import ru.mrroot.gitclientv6.data.user.GitHubUser
import ru.mrroot.gitclientv6.data.user.GitHubUserRepos

class CloudUserDataSource(private val gitHubApi: GitHubApi) : UserDataSource {
    override fun getUsers(): Single<List<GitHubUser>> =
        gitHubApi.getUsers()

    override fun getUserByLogin(userId: String): Maybe<GitHubUser> =
        gitHubApi.getUserByLogin(userId)
            .toMaybe()

    override fun getUserRepos(reposUrl: String): Single<List<GitHubUserRepos>> =
        gitHubApi.getUserRepositories(reposUrl)
            .map {
                it.forEach { repo ->
                    repo.reposUrl = reposUrl
                }
                it
            }
}