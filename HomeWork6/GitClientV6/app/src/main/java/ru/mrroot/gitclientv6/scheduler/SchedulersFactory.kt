package ru.mrroot.gitclientv6.scheduler

object SchedulersFactory {
    fun create(): Schedulers = DefaultSchedulers()
}