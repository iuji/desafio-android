package com.picpay.desafio.android.features.home

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.domain.entities.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private var listItemNameView: TextView? = null
    private var listItemUsernameView: TextView? = null
    private var listItemPictureView: CircleImageView? = null
    private var listItemProgressBarView: ProgressBar? = null

    fun bind(user: User?) {
        listItemNameView?.text = user?.name
        listItemUsernameView?.text = user?.username
        listItemProgressBarView?.visibility = View.VISIBLE
        Picasso.get()
            .load(user?.img)
            .error(R.drawable.ic_round_account_circle)
            .into(listItemPictureView, object : Callback {
                override fun onSuccess() {
                    listItemProgressBarView?.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    listItemProgressBarView?.visibility = View.GONE
                }
            })
    }

    init {
        listItemNameView = itemView.findViewById(R.id.name)
        listItemUsernameView = itemView.findViewById(R.id.username)
        listItemPictureView = itemView.findViewById(R.id.picture)
        listItemProgressBarView = itemView.findViewById(R.id.progressBar)
    }
}