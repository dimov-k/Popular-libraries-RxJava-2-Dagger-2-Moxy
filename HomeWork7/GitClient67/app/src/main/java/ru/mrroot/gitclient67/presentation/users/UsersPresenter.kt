package ru.mrroot.gitclient67.presentation.users

import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.mrroot.gitclient67.data.user.GitHubUser
import ru.mrroot.gitclient67.data.user.GitHubUserRepository
import ru.mrroot.gitclient67.presentation.user.UserScreen
import ru.mrroot.gitclient67.presentation.users.adapter.UserItemView
import ru.mrroot.gitclient67.scheduler.Schedulers

class UsersPresenter @AssistedInject constructor(
    private val usersRepo: GitHubUserRepository,
    private val router: Router,
    private val schedulers: Schedulers
) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login.uppercase())
            view.setAvatar(user.avatar)
        }
    }

    val usersListPresenter = UsersListPresenter()
    var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(UserScreen(usersListPresenter.users[itemView.pos].login))
        }
    }

    fun loadData() {
        disposable += usersRepo
            .getUsers()
            .observeOn(schedulers.background())
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                {
                    usersListPresenter.users.addAll(it)
                    viewState.updateList()
                },
                {
                    viewState.showToast("Repo Error: ${it.message}")
                }
            )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}