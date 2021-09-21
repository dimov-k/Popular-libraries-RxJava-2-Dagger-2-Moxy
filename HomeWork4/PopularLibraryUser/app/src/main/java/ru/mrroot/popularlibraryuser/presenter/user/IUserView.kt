package ru.mrroot.popularlibraryuser.presenter.user

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.mrroot.popularlibraryuser.domain.model.GithubUser

@SingleState
interface IUserView : MvpView {

    fun showUser(user: GithubUser)

    fun showMessage(message: String)
}