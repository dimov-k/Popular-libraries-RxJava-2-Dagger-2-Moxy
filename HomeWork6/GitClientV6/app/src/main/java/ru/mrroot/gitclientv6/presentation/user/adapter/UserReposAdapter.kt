package ru.mrroot.gitclientv6.presentation.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mrroot.gitclientv6.R.layout.item_repos
import ru.mrroot.gitclientv6.presentation.GitHubUserReposViewModel

class UserReposAdapter(private val delegate: DelegateRepos) :
    ListAdapter<GitHubUserReposViewModel, UserReposViewHolder>(UserReposDiff) {

    interface DelegateRepos {
        fun onReposPicked(repos: GitHubUserReposViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposViewHolder =
        UserReposViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(item_repos, parent, false)
        )

    override fun onBindViewHolder(holder: UserReposViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}