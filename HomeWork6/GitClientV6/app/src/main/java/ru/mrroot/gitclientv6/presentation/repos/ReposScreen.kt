package ru.mrroot.gitclientv6.presentation.repos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel

class ReposScreen(private val repos: GitHubUserReposViewModel) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ReposFragment.newInstance(repos)

}