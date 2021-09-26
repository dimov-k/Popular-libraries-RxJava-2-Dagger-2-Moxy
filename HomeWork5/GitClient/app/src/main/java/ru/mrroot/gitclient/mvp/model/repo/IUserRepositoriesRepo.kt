package ru.mrroot.gitclient.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.mrroot.gitclient.mvp.model.entity.UserRepository

interface IUserRepositoriesRepo {
    fun getRepositories(url: String): Single<List<UserRepository>>
}