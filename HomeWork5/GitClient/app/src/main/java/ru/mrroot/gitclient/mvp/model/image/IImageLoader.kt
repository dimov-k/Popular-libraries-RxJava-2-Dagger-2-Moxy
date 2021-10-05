package ru.mrroot.gitclient.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}