package ru.mrroot.popularlibraryuser.scheduler

object SchedulerFactory {
    fun create(): Schedulers = DefaultSchedulers()
}