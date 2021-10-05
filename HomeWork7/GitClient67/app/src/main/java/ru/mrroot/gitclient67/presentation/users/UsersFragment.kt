package ru.mrroot.gitclient67.presentation.users

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.mrroot.gitclient67.R.layout.fragment_users
import ru.mrroot.gitclient67.arguments
import ru.mrroot.gitclient67.databinding.FragmentUsersBinding
import ru.mrroot.gitclient67.presentation.abs.AbsFragment
import ru.mrroot.gitclient67.presentation.users.adapter.UsersRVAdapter
import javax.inject.Inject

class UsersFragment : AbsFragment(fragment_users), UsersView {
    companion object {
        fun newInstance(): Fragment =
            UsersFragment()
                .arguments()
    }

    @Inject
    lateinit var presenterFactory: UsersPresenterFactory

    val presenter: UsersPresenter by moxyPresenter {
        presenterFactory.create()
    }
    var adapter: UsersRVAdapter? = null

    private val vb: FragmentUsersBinding by viewBinding()


    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}