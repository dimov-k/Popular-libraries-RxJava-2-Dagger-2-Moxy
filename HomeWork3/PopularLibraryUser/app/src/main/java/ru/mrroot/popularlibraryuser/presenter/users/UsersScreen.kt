package ru.mrroot.popularlibraryuser.presenter.users

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.mrroot.popularlibraryuser.ui.UsersFragment

object UsersScreen {
    fun create() = FragmentScreen { UsersFragment.newInstance() }
}