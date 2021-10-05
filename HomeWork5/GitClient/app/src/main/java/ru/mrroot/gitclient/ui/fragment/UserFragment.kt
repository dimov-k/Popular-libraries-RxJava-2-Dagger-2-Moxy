package ru.mrroot.gitclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mrroot.gitclient.databinding.FragmentUserBinding
import ru.mrroot.gitclient.mvp.model.api.ApiHolder
import ru.mrroot.gitclient.mvp.model.entity.GithubUser
import ru.mrroot.gitclient.mvp.model.repo.UserRepositoriesRepo
import ru.mrroot.gitclient.mvp.presenter.UserPresenter
import ru.mrroot.gitclient.mvp.view.UserView
import ru.mrroot.gitclient.ui.App
import ru.mrroot.gitclient.ui.BackButtonListener
import ru.mrroot.gitclient.ui.adapter.UserReposRVAdapter
import ru.mrroot.gitclient.ui.navigation.AndroidScreens

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user =
            arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser //При отсутствии аргумента приложение упадет. Так задумано.
        UserPresenter(AndroidSchedulers.mainThread(), App.instance.router, user, UserRepositoriesRepo(
            ApiHolder.api), AndroidScreens())
    }

    var adapter: UserReposRVAdapter? = null

    private var vb: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvRepos?.layoutManager = LinearLayoutManager(context)
        adapter = UserReposRVAdapter(presenter.userReposListPresenter)
        vb?.rvRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setLogin(text: String) {
        vb?.tvLogin?.text = text
    }

    override fun backPressed() = presenter.backPressed()
}