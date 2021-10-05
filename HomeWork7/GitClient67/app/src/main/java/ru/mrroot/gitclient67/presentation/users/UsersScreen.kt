package ru.mrroot.gitclient67.presentation.users

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UsersScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        UsersFragment.newInstance()

}