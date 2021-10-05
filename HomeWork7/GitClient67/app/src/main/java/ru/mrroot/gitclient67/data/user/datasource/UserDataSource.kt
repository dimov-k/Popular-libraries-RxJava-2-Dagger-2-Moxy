package ru.mrroot.gitclient67.data.user.datasource

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.mrroot.gitclient67.data.user.GitHubUser
import ru.mrroot.gitclient67.data.user.GitHubUserRepos

interface UserDataSource {
    fun getUsers(): Single<List<GitHubUser>>

    fun getUserByLogin(userId: String): Maybe<GitHubUser>

    fun getUserRepos(reposUrl: String): Single<List<GitHubUserRepos>>
}