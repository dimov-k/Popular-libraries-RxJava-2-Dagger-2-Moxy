package ru.mrroot.gitclient.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.mrroot.gitclient.mvp.model.entity.GithubUser
import ru.mrroot.gitclient.mvp.model.entity.UserRepository


interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRepositories(@Url url: String) : Single<List<UserRepository>>

}