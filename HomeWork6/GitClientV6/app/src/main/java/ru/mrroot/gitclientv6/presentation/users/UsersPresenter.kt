package ru.mrroot.gitclientv6.presentation.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.mrroot.gitclientv6.data.user.GitHubUser
import ru.mrroot.gitclientv6.data.user.GitHubUserRepository
import ru.mrroot.gitclientv6.presentation.IScreens
import ru.mrroot.gitclientv6.presentation.users.adapter.UserItemView
import ru.mrroot.gitclientv6.scheduler.Schedulers

class UsersPresenter(
    private val usersRepo: GitHubUserRepository,
    private val router: Router,
    private val screens: IScreens,
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
            router.navigateTo(screens.user(usersListPresenter.users[itemView.pos].login))
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

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}