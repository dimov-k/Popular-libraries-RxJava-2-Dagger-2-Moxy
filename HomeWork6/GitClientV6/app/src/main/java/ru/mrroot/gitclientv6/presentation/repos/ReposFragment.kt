package ru.mrroot.gitclientv6.presentation.repos

import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mrroot.gitclientv6.R.layout.fragment_repos
import ru.mrroot.gitclientv6.databinding.FragmentReposBinding
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel

class ReposFragment(repos: GitHubUserReposViewModel? = null) : MvpAppCompatFragment(fragment_repos),
    ReposView {

    companion object Factory {
        fun newInstance(repos: GitHubUserReposViewModel): Fragment =
            ReposFragment(repos)
    }

    private val viewBinding: FragmentReposBinding by viewBinding()

    override fun showRepos(repos: GitHubUserReposViewModel) {
        viewBinding.tvReposName.text = repos.name
        viewBinding.tvReposDescription.text = repos.description
        viewBinding.tvReposLanguage.text = repos.language
        viewBinding.tvReposForks.text = repos.forks_count
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    @Suppress("unused")
    private val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(repos)
    }

}

