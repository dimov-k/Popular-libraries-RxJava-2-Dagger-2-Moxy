package ru.mrroot.gitclientv6.presentation.repos

import moxy.MvpPresenter
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel

class ReposPresenter(private val repos: GitHubUserReposViewModel?) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        repos?.let { viewState.showRepos(repos) }
    }

}