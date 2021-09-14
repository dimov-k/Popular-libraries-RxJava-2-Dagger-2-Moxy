package ru.mrroot.popularlibraryuser.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mrroot.popularlibraryuser.App.Navigation.router
import ru.mrroot.popularlibraryuser.R
import ru.mrroot.popularlibraryuser.databinding.FragmentUserBinding
import ru.mrroot.popularlibraryuser.domain.model.GithubUser
import ru.mrroot.popularlibraryuser.presenter.user.IUserView
import ru.mrroot.popularlibraryuser.presenter.user.UserPresenter
import ru.mrroot.popularlibraryuser.ui.extensions.showSnakeBar

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), IUserView {
    companion object {

        private const val ARG_USER = "arg_user"

        fun newInstance(user: GithubUser): Fragment = UserFragment().apply {
            arguments = bundleOf(ARG_USER to user)
        }
    }

    private val vb: FragmentUserBinding by viewBinding()

    private val user: GithubUser? by lazy { arguments?.getParcelable(ARG_USER) }
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            user = user,
            router = router
        )
    }

    override fun showUser(user: GithubUser) {
        vb.userLogin.text = user.login
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }
}