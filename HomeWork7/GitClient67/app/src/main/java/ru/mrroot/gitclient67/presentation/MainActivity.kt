package ru.mrroot.gitclient67.presentation

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.mrroot.gitclient67.R
import ru.mrroot.gitclient67.databinding.ActivityMainBinding
import ru.mrroot.gitclient67.presentation.abs.AbsActivity
import ru.mrroot.gitclient67.presentation.users.UsersScreen
import javax.inject.Inject

class MainActivity : AbsActivity() {
    val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        savedInstanceState ?: router.newRootScreen(UsersScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}