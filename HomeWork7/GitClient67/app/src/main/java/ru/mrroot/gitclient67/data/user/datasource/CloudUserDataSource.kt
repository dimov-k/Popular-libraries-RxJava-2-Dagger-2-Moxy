package ru.mrroot.gitclient67.data.user.datasource


import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.mrroot.gitclient67.data.api.GitHubApi
import ru.mrroot.gitclient67.data.user.GitHubUser
import ru.mrroot.gitclient67.data.user.GitHubUserRepos
import javax.inject.Inject

class CloudUserDataSource @Inject constructor(
    private val gitHubApi: GitHubApi
) : UserDataSource {
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