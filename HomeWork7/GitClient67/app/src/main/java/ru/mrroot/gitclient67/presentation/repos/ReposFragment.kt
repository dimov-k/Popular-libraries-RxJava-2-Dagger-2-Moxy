package ru.mrroot.gitclient67.presentation.repos

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.mrroot.gitclient67.R.layout.fragment_repos
import ru.mrroot.gitclient67.databinding.FragmentReposBinding
import ru.mrroot.gitclient67.presentation.GitHubUserReposViewModel
import ru.mrroot.gitclient67.presentation.abs.AbsFragment

class ReposFragment : AbsFragment(fragment_repos),
    ReposView {

    companion object Factory {
        private const val ARG_REPOS = "arg_repos"
        fun newInstance(repos: GitHubUserReposViewModel): Fragment =
            ReposFragment().apply {
                this.arguments = bundleOf(ARG_REPOS to repos)
            }
    }

    private val repos: GitHubUserReposViewModel by lazy {
        arguments?.getParcelable(ARG_REPOS) ?: GitHubUserReposViewModel()
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

