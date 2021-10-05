package ru.mrroot.gitclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mrroot.gitclient.databinding.FragmentUsersBinding
import ru.mrroot.gitclient.mvp.model.api.ApiHolder
import ru.mrroot.gitclient.mvp.model.repo.GithubUsersRepo
import ru.mrroot.gitclient.mvp.presenter.UsersPresenter
import ru.mrroot.gitclient.mvp.view.UsersView
import ru.mrroot.gitclient.ui.App
import ru.mrroot.gitclient.ui.BackButtonListener
import ru.mrroot.gitclient.ui.adapter.UsersRVAdapter
import ru.mrroot.gitclient.ui.image.GlideImageLoader
import ru.mrroot.gitclient.ui.navigation.AndroidScreens

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            GithubUsersRepo(ApiHolder.api),
            App.instance.router, AndroidScreens()
        )
    }

    var adapter: UsersRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}