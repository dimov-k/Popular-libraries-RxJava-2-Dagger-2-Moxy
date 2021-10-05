package ru.mrroot.gitclient67.presentation.users.adapter

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun setAvatar(text: String)
}