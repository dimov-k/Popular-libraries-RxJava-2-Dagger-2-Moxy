package ru.mrroot.popularlibraryuser.domain.repository

import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Single

interface IUsersRepository {

    /**
     * Получить список пользователей
     * @return Список пользователей List of GithubUser
     */
    fun users(): Single<List<GithubUser>>

    /**
     * Получить пользователя по userID
     * @param userId Id пользователя
     * @return GithubUser
     */
    fun userById(userId: Int): Maybe<GithubUser>
}