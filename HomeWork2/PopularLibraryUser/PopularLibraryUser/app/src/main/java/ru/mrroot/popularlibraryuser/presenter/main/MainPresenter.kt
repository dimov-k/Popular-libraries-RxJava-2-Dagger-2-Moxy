package ru.mrroot.popularlibraryuser.presenter.main

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.mrroot.popularlibraryuser.presenter.users.UsersScreen

class MainPresenter(
    private val router: Router,
) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen.create())
    }

    fun back() = router.exit()
}