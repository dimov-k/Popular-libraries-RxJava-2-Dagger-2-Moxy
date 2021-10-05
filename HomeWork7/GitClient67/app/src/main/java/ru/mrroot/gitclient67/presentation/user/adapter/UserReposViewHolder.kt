package ru.mrroot.gitclient67.presentation.user.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mrroot.gitclient67.click
import ru.mrroot.gitclient67.databinding.ItemReposBinding
import ru.mrroot.gitclient67.presentation.GitHubUserReposViewModel

class UserReposViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val viewBinding: ItemReposBinding by viewBinding()

    fun bind(repos: GitHubUserReposViewModel, delegate: UserReposAdapter.DelegateRepos?) {
        with(viewBinding) {
            userRepository.text = repos.name

            root.click { delegate?.onReposPicked(repos) }
        }
    }

}