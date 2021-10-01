package ru.mrroot.gitclientv6

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.plugins.RxJavaPlugins


@SuppressLint("StaticFieldLeak")
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    object ContextHolder {

        lateinit var context: Context

    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = applicationContext
        RxJavaPlugins.setErrorHandler {  }
        instance = this
    }
}