package com.example.dummy.data.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dummy.R
import com.example.dummy.data.network.model.User
import com.squareup.picasso.Picasso


class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var userList : List<User> = ArrayList()

    fun setUserList(userList: List<User>){
        this.userList = userList
        notifyDataSetChanged()
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val userAvatar = v.findViewById<AppCompatImageView>(R.id.user_avtar)
        val userId = v.findViewById<AppCompatTextView>(R.id.user_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_card_layout, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            // TODO: Show complete profile of user using getUserMeta service
            val position = holder.adapterPosition
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(userList[position].html_url))
            context.startActivity(browserIntent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userList[position].login

        Picasso.get()
                .load(userList[position].avatar_url)
                .error(R.drawable.ic_avtar)
                .into(holder.userAvatar)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}