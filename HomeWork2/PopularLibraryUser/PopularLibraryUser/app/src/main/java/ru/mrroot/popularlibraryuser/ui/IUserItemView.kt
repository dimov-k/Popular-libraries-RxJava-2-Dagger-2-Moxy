package ru.mrroot.popularlibraryuser.ui

import ru.mrroot.popularlibraryuser.presenter.user.IItemView

interface IUserItemView : IItemView {
    fun setLogin(text: String)
}