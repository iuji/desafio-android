package com.picpay.desafio.android.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.domain.entities.User

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    private var mUsers: List<User>? = null

    fun setUsers(users: List<User>) {
        this.mUsers = users
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UserListItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(mUsers?.get(position))
    }

    override fun getItemCount(): Int = mUsers?.size ?: 0
}