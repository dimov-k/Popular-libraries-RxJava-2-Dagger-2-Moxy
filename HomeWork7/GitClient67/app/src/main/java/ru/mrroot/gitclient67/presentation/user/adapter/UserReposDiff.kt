package ru.mrroot.gitclient67.presentation.user.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.mrroot.gitclient67.presentation.GitHubUserReposViewModel


object UserReposDiff : DiffUtil.ItemCallback<GitHubUserReposViewModel>() {

    private val payload = Any()

    override fun areItemsTheSame(
        oldItem: GitHubUserReposViewModel,
        newItem: GitHubUserReposViewModel
    ): Boolean {
        return oldItem.name == newItem.name
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: GitHubUserReposViewModel,
        newItem: GitHubUserReposViewModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(
        oldItem: GitHubUserReposViewModel,
        newItem: GitHubUserReposViewModel
    ) = payload

}

