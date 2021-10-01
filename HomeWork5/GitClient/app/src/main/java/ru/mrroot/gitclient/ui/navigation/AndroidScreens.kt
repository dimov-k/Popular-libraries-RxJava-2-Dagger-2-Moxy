package ru.mrroot.gitclient.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.mrroot.gitclient.mvp.model.entity.GithubUser
import ru.mrroot.gitclient.mvp.model.entity.UserRepository
import ru.mrroot.gitclient.mvp.model.navigation.IScreens
import ru.mrroot.gitclient.ui.fragment.RepositoryFragment
import ru.mrroot.gitclient.ui.fragment.UserFragment
import ru.mrroot.gitclient.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(githubUser: GithubUser) = FragmentScreen { UserFragment.newInstance(githubUser) }
    override fun repository(userRepository: UserRepository) = FragmentScreen { RepositoryFragment.newInstance(userRepository) }
}