package ru.mrroot.gitclientv6.data.user


import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable


interface GitHubUserRepository {
    fun getUsers(): Observable<List<GitHubUser>>


    fun getUserByLogin(userId: String): Maybe<GitHubUser>

    fun getUserRepositories(reposUrl: String): Observable<List<GitHubUserRepos>>
}