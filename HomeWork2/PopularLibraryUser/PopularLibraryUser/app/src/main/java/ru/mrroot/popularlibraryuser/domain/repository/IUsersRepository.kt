package ru.mrroot.popularlibraryuser.domain.repository

import ru.mrroot.popularlibraryuser.domain.model.GithubUser

interface IUsersRepository {

    //Список пользователей
    fun users(): List<GithubUser>
}