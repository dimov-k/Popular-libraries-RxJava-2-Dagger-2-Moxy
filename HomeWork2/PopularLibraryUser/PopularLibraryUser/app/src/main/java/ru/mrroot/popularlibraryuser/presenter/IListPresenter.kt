package ru.mrroot.popularlibraryuser.presenter

import ru.mrroot.popularlibraryuser.presenter.user.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}