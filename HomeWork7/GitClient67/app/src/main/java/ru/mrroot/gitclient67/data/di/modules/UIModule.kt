package ru.mrroot.gitclient67.data.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mrroot.gitclient67.presentation.MainActivity
import ru.mrroot.gitclient67.presentation.repos.ReposFragment
import ru.mrroot.gitclient67.presentation.user.UserFragment
import ru.mrroot.gitclient67.presentation.users.UsersFragment

@Module
interface UIModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindReposFragment(): ReposFragment
}