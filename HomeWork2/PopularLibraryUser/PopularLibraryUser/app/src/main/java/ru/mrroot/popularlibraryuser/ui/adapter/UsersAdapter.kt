package ru.mrroot.popularlibraryuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mrroot.popularlibraryuser.databinding.UserBinding
import ru.mrroot.popularlibraryuser.presenter.IUserListPresenter
import ru.mrroot.popularlibraryuser.ui.IUserItemView

class UsersAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            UserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ItemViewHolder(
        private val vb: UserBinding
    ) : RecyclerView.ViewHolder(vb.root),
        IUserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }
    }
}