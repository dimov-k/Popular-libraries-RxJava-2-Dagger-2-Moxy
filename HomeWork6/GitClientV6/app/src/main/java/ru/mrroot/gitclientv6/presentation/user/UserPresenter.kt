package ru.mrroot.gitclientv6.presentation.user

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.mrroot.gitclientv6.data.user.GitHubUserRepository
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel
import ru.mrroot.gitclientv6.presentation.repos.ReposScreen
import ru.mrroot.gitclientv6.scheduler.Schedulers

class UserPresenter(
    val router: Router,
    val textLogin: String,
    private val userRepository: GitHubUserRepository,
    private val schedulers: Schedulers
) : MvpPresenter<UserView>() {
    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable +=
            userRepository
                .getUserByLogin(textLogin)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    {
                        viewState.setLogin(it.login.uppercase(), it.reposUrl)
                        viewState.setAvatar(it.avatar)
                    },
                    viewState::showError
                )
    }

    fun setRepos(reposUrl: String) {
        disposable += userRepository
            .getUserRepositories(reposUrl)
            .observeOn(schedulers.background())
            .map { repos -> repos.map(GitHubUserReposViewModel.Mapper::map) }
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                viewState::showRepos,
                viewState::showError
            )
    }

    fun displayRepos(repos: GitHubUserReposViewModel) {
        router.navigateTo(ReposScreen(repos))
    }

    override fun onDestroy() {
        disposable.clear()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}