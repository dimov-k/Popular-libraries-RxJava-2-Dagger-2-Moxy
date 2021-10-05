package ru.mrroot.popularlibraryuser.domain.repository

object UserRepositoryFactory {

    fun create(): IUsersRepository = MockUsersRepositoryImpl
}