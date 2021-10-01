package ru.mrroot.popularlibraryuser.presenter.user

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import ru.mrroot.popularlibraryuser.ui.UserFragment

class UserScreen(private val userId: Int) {
    fun create(): Screen = FragmentScreen { UserFragment.newInstance(userId) }
}