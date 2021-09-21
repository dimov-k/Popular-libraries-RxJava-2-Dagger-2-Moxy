package ru.mrroot.popularlibraryuser.domain.repository

import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Single

object MockUsersRepositoryImpl : IUsersRepository {
    private val users = listOf(
        GithubUser(userId = 0, login = "Димов К.К"),
        GithubUser(userId = 1, login = "User_2"),
        GithubUser(userId = 2, login = "Бендер В.И."),
        GithubUser(userId = 3, login = "User_3"),
        GithubUser(userId = 4, login = "User_4"),
        GithubUser(userId = 5, login = "User_5")
    )

    override fun users(): Single<List<GithubUser>> = Single.just(users)

    override fun userById(userId: Int): Maybe<GithubUser> =
        users.firstOrNull { user -> user.userId == userId }
            ?.let { user -> Maybe.just(user) }
            ?: Maybe.error(Exception("Выбран несуществующий пользователь."))
}