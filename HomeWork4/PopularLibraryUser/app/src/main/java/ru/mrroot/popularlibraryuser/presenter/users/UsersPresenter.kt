package ru.mrroot.popularlibraryuser.presenter.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import ru.mrroot.popularlibraryuser.domain.repository.IUsersRepository
import ru.mrroot.popularlibraryuser.domain.repository.MockUsersRepositoryImpl
import ru.mrroot.popularlibraryuser.presenter.IUserListPresenter
import ru.mrroot.popularlibraryuser.presenter.user.UserScreen
import ru.mrroot.popularlibraryuser.ui.IUserItemView

class UsersPresenter(
    private val repository: IUsersRepository,
    private val router: Router
) :
    MvpPresenter<IUsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {

        repository
            .users()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                viewState.showMessage(it.message.toString())
            })
            .addTo(disposables)

        usersListPresenter.itemClickListener = { itemView ->
            Log.d("popLibDEBUG", itemView.toString())
            router.navigateTo(UserScreen(usersListPresenter.users[itemView.pos].userId).create())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}