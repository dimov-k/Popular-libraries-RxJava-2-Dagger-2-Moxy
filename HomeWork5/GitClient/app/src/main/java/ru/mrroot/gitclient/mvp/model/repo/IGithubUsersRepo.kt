package ru.mrroot.gitclient.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.mrroot.gitclient.mvp.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}