package ru.mrroot.popularlibraryuser.presenter.user

import android.os.Handler
import android.os.Looper
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import ru.mrroot.popularlibraryuser.presenter.users.UsersScreen

class UserPresenter(
    private val user: GithubUser?,
    private val router: Router
) :
    MvpPresenter<IUserView>() {

    companion object {
        const val CLOSE_DELAY = 800L
        const val ERROR_MESSAGE = "Не выбран пользователь!"
    }

    override fun onFirstViewAttach() {
        if (user != null) {
            viewState.showUser(user)
        } else {
            viewState.showMessage(ERROR_MESSAGE)
            Thread {
                Handler(Looper.getMainLooper()).postDelayed({
                    router.replaceScreen(UsersScreen.create())
                }, CLOSE_DELAY)
            }.start()
        }
    }
}