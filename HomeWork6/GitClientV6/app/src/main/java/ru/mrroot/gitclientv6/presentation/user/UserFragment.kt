package ru.mrroot.gitclientv6.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mrroot.gitclientv6.App
import ru.mrroot.gitclientv6.data.user.GitHubUserRepositoryFactory
import ru.mrroot.gitclientv6.databinding.FragmentUserBinding
import ru.mrroot.gitclientv6.presentation.BackButtonListener
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel
import ru.mrroot.gitclientv6.presentation.user.adapter.UserReposAdapter
import ru.mrroot.gitclientv6.scheduler.SchedulersFactory
import ru.mrroot.gitclientv6.setStartDrawableCircleImageFromUri

class UserFragment(val textLogin: String = "") : MvpAppCompatFragment(), UserView,
    BackButtonListener, UserReposAdapter.DelegateRepos {
    companion object {
        fun newInstance(textLogin: String) = UserFragment(textLogin)
    }

    private var vb: FragmentUserBinding? = null
    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            App.instance.router,
            textLogin,
            GitHubUserRepositoryFactory.create(),
            SchedulersFactory.create()
        )
    }

    private val reposAdapter = UserReposAdapter(delegate = this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb?.rvUserRepos?.adapter = reposAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun setLogin(text: String, reposUrl: String) {
        vb?.tvLoginUser?.text = text
        presenter.setRepos(reposUrl)
    }

    override fun setAvatar(text: String) {
        vb?.tvLoginUser?.setStartDrawableCircleImageFromUri(text)
    }

    override fun showRepos(repos: List<GitHubUserReposViewModel>) {
        reposAdapter.submitList(repos)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onReposPicked(repos: GitHubUserReposViewModel) =
        presenter.displayRepos(repos)
}