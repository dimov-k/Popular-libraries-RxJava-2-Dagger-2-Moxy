package ru.mrroot.gitclientv6.presentation.users.adapter

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun setAvatar(text: String)
}