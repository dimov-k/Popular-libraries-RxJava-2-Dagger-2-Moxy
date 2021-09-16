package ru.mrroot.popularlibraryuser.presenter.main

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.mrroot.popularlibraryuser.presenter.users.UsersScreen
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainPresenter(
    private val router: Router,
) : MvpPresenter<IMainView>() {

    companion object {
        const val RND_BOUND = 1000
    }

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen.create())
    }

    fun back() = router.exit()

    private fun createJustOne() = Observable.just("1", "2", "3", "4")
    private fun createJustTwo() = Observable.just("1", "2", "3", "4")

    @SuppressLint("CheckResult")
    fun switchMap() {
        createJustOne()
            .switchMap {
                val delay = Random.nextInt(RND_BOUND).toLong()
                return@switchMap Observable.just(it + "x").delay(
                    delay,
                    TimeUnit.MILLISECONDS
                )
            }
            .subscribe(
                { string -> Log.d("popLibDEBUG", "switchMap onNext: $string") },
                { Log.d("popLibDEBUG", "onError: ${it.message}") }
            )
            .addTo(disposables)
    }

    @SuppressLint("CheckResult")
    fun flatMap() {
        createJustTwo()
            .flatMap {
                val delay = Random.nextInt(RND_BOUND).toLong()
                return@flatMap Observable.just(it + "x").delay(
                    delay,
                    TimeUnit.MILLISECONDS
                )
            }
            .subscribe(
                { string -> Log.d("popLibDEBUG", "flatMap: onNext: $string") },
                { Log.d("popLibDEBUG", "onError: ${it.message}") }
            )
            .addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}