package ru.mrroot.gitclient.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.mrroot.gitclient.mvp.model.api.IDataSource

class GithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}