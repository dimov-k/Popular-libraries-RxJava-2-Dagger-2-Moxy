package ru.mrroot.gitclient67.data.api


import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ru.mrroot.gitclient67.data.user.GitHubUser
import ru.mrroot.gitclient67.data.user.GitHubUserRepos

interface GitHubApi {
    @GET("/users")
    fun getUsers(@Query("since") since: Int? = null): Single<List<GitHubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(@Path("username") login: String): Single<GitHubUser>

    @GET
    fun getUserRepositories(@Url reposUrl: String): Single<List<GitHubUserRepos>>
}