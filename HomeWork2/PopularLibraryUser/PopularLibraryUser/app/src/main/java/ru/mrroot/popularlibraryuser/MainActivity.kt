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
import ru.mrroot.popularlibraryuser.presenter.main.IMainView
import ru.mrroot.popularlibraryuser.presenter.main.MainPresenter
import ru.mrroot.popularlibraryuser.ui.IBackButtonListener

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), IMainView {

    private val vb: ActionProvider by viewBinding()

    private val presenter by moxyPresenter { MainPresenter(router) }
    val navigator = AppNavigator(this, R.id.container)

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
}