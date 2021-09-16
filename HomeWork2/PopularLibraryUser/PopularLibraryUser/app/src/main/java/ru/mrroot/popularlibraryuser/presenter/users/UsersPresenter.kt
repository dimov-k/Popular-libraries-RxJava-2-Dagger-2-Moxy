package ru.mrroot.popularlibraryuser.presenter.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import ru.mrroot.popularlibraryuser.domain.repository.MockUsersRepositoryImpl
import ru.mrroot.popularlibraryuser.presenter.IUserListPresenter
import ru.mrroot.popularlibraryuser.presenter.user.UserScreen
import ru.mrroot.popularlibraryuser.ui.IUserItemView

class UsersPresenter(
    private val mockUsersRepositoryImpl: MockUsersRepositoryImpl,
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = mockUsersRepositoryImpl.users()

        usersListPresenter.itemClickListener = { itemView ->
            Log.d("popLibDEBUG", itemView.toString())
            router.navigateTo(UserScreen(users[itemView.pos]).create())
        }

        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}