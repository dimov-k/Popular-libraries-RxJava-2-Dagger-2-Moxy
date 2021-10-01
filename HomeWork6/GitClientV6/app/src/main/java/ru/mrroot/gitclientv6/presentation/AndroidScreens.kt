package ru.mrroot.gitclientv6.presentation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.mrroot.gitclientv6.presentation.user.UserFragment
import ru.mrroot.gitclientv6.presentation.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(textLogin: String) = FragmentScreen { UserFragment.newInstance(textLogin) }
}
