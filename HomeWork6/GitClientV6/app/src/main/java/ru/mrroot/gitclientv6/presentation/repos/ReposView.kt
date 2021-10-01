package ru.mrroot.gitclientv6.presentation.repos

import moxy.viewstate.strategy.alias.SingleState
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel
import ru.mrroot.gitclientv6.presentation.ScreenView

interface ReposView : ScreenView {

    @SingleState
    fun showRepos(repos: GitHubUserReposViewModel)

}