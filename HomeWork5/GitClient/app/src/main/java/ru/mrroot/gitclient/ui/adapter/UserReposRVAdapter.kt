package ru.mrroot.gitclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mrroot.gitclient.databinding.ItemRepositoryBinding
import ru.mrroot.gitclient.mvp.presenter.list.IUserReposListPresenter
import ru.mrroot.gitclient.mvp.view.list.RepositoryItemView

class UserReposRVAdapter(val presenter: IUserReposListPresenter) :
    RecyclerView.Adapter<UserReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserReposRVAdapter.ViewHolder =
        ViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: UserReposRVAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(vb.root),
        RepositoryItemView {

        override var pos = -1

        override fun setName(text: String) = with(vb) {
            tvRepoName.text = text
        }
    }
}