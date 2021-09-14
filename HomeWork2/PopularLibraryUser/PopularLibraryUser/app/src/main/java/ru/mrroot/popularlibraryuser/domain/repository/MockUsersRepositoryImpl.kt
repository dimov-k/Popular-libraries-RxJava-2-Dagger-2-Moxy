package ru.mrroot.popularlibraryuser.domain.repository

import ru.mrroot.popularlibraryuser.domain.model.GithubUser

object MockUsersRepositoryImpl : IUsersRepository {
    private val users = listOf(
        GithubUser("Димов К.К."),
        GithubUser("User2"),
        GithubUser("Бендер И.В."),
        GithubUser("User4"),
        GithubUser("Пискунов В.А."),
        GithubUser("User5")
    )

    override fun users(): List<GithubUser> {
        return users
    }
}