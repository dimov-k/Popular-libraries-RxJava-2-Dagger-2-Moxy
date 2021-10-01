package ru.mrroot.popularlibraryuser.domain.repository

import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Single

interface IUsersRepository {

    //Список пользователей
    fun users(): Single<List<GithubUser>>

    //Получить пользователя по userID
    fun userById(userId: Int): Maybe<GithubUser>
}