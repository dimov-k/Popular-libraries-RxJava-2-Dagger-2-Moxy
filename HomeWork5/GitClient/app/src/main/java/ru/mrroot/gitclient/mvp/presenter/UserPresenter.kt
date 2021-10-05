package ru.mrroot.gitclient.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.mrroot.gitclient.mvp.model.entity.GithubUser
import ru.mrroot.gitclient.mvp.model.entity.UserRepository
import ru.mrroot.gitclient.mvp.model.navigation.IScreens
import ru.mrroot.gitclient.mvp.model.repo.IUserRepositoriesRepo
import ru.mrroot.gitclient.mvp.presenter.list.IUserReposListPresenter
import ru.mrroot.gitclient.mvp.view.UserView
import ru.mrroot.gitclient.mvp.view.list.RepositoryItemView

class UserPresenter(
    private val uiScheduler: Scheduler,
    private val router: Router,
    private val user: GithubUser,
    private val userReposRepo: IUserRepositoriesRepo,
    val screens: IScreens
) : MvpPresenter<UserView>() {

    class UserReposListPresenter : IUserReposListPresenter {
        val repos = mutableListOf<UserRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepositoryItemView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setName(it) }
        }
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        user.login?.let { viewState.setLogin(it) }
        loadData()

        userReposListPresenter.itemClickListener = { itemView ->
            val repo = userReposListPresenter.repos[itemView.pos]
            router.navigateTo(screens.repository(repo))
        }
    }

    private fun loadData() {
        user.reposUrl?.let { url ->
            userReposRepo.getRepositories(url)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    userReposListPresenter.repos.clear()
                    userReposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
