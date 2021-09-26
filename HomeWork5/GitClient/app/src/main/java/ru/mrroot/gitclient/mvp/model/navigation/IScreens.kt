package ru.mrroot.gitclient.mvp.model.navigation

import com.github.terrakok.cicerone.Screen
import ru.mrroot.gitclient.mvp.model.entity.GithubUser
import ru.mrroot.gitclient.mvp.model.entity.UserRepository

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser): Screen
    fun repository(userRepository: UserRepository): Screen
}