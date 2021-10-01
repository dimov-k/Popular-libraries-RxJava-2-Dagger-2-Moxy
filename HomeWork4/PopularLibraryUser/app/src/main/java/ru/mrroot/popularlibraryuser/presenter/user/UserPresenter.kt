package ru.mrroot.popularlibraryuser.presenter.user

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import ru.mrroot.popularlibraryuser.domain.repository.IUsersRepository
import ru.mrroot.popularlibraryuser.domain.repository.MockUsersRepositoryImpl
import ru.mrroot.popularlibraryuser.presenter.users.UsersScreen

class UserPresenter(
    private val userId: Int,
    private val router: Router,
    private val repository: IUsersRepository,
) :
    MvpPresenter<IUserView>() {

    private var disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        repository
            .userById(userId)
            .subscribe(
                viewState::showUser
            ) {
                viewState.showMessage(it.message.toString())
                router.replaceScreen(UsersScreen.create())
            }.addTo(disposables)
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}