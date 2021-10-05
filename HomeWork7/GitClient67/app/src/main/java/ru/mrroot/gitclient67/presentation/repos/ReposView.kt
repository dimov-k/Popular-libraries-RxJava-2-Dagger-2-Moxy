package ru.mrroot.gitclient67.presentation.repos

import moxy.viewstate.strategy.alias.SingleState
import ru.mrroot.gitclient67.presentation.GitHubUserReposViewModel
import ru.mrroot.gitclient67.presentation.ScreenView

interface ReposView : ScreenView {

    @SingleState
    fun showRepos(repos: GitHubUserReposViewModel)

}