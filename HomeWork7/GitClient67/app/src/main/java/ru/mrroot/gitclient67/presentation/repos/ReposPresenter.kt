package ru.mrroot.gitclient67.presentation.repos

import moxy.MvpPresenter
import ru.mrroot.gitclient67.presentation.GitHubUserReposViewModel

class ReposPresenter(private val repos: GitHubUserReposViewModel?) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        repos?.let { viewState.showRepos(repos) }
    }

}