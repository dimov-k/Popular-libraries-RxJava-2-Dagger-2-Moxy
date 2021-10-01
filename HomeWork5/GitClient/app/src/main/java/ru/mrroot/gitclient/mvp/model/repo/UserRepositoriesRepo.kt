package ru.mrroot.gitclient.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.mrroot.gitclient.mvp.model.api.IDataSource
import ru.mrroot.gitclient.mvp.model.entity.UserRepository

class UserRepositoriesRepo(private val api: IDataSource) : IUserRepositoriesRepo {

    override fun getRepositories(url: String): Single<List<UserRepository>> =
        api.getUserRepositories(url).subscribeOn(Schedulers.io())
}