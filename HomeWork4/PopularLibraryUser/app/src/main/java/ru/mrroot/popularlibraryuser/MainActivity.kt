package ru.mrroot.popularlibraryuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ActionProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.mrroot.popularlibraryuser.App.Navigation.navigatorHolder
import ru.mrroot.popularlibraryuser.App.Navigation.router
import ru.mrroot.popularlibraryuser.databinding.ActivityMainBinding
import ru.mrroot.popularlibraryuser.presenter.convert.ConvertScreen
import ru.mrroot.popularlibraryuser.presenter.main.IMainView
import ru.mrroot.popularlibraryuser.presenter.main.MainPresenter
import ru.mrroot.popularlibraryuser.presenter.users.UsersScreen
import ru.mrroot.popularlibraryuser.ui.IBackButtonListener

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), IMainView {

    private val vb: ActivityMainBinding by viewBinding()

    private val presenter by moxyPresenter { MainPresenter(router) }
    private val navigator = AppNavigator(this, R.id.container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.back()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        vb.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_users -> {
                    router.replaceScreen(UsersScreen.create())
                    true
                }
                R.id.navigation_convert -> {
                    router.replaceScreen(ConvertScreen().create())
                    true
                }
                else -> false
            }
        }

        router.replaceScreen(UsersScreen.create())
    }

}