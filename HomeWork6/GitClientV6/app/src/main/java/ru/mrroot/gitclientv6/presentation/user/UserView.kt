package ru.mrroot.gitclientv6.presentation.user

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel
import ru.mrroot.gitclientv6.presentation.ScreenView

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : ScreenView {
    fun setLogin(text: String, reposUrl: String)
    fun setAvatar(text: String)
    fun showRepos(repos: List<GitHubUserReposViewModel>)
}