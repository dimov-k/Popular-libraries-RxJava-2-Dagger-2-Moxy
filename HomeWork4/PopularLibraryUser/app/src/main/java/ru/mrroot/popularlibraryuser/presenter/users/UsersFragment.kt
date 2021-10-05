package ru.mrroot.popularlibraryuser.presenter.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mrroot.popularlibraryuser.App
import ru.mrroot.popularlibraryuser.R
import ru.mrroot.popularlibraryuser.databinding.FragmentUsersBinding
import ru.mrroot.popularlibraryuser.domain.repository.UserRepositoryFactory
import ru.mrroot.popularlibraryuser.ui.IBackButtonListener
import ru.mrroot.popularlibraryuser.ui.adapter.UsersAdapter
import ru.mrroot.popularlibraryuser.ui.extensions.showSnakeBar

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), IUsersView,
    IBackButtonListener {
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            UserRepositoryFactory.create(),
            App.router
        )
    }
    var adapter: UsersAdapter? = null

    private val vb: FragmentUsersBinding by viewBinding()

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.users_title)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }

    override fun backPressed() = presenter.backPressed()
}