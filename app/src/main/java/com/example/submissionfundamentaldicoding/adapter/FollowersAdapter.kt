package com.example.submissionfundamentaldicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.model.UserFollowersItem
import kotlinx.android.synthetic.main.item_followers.view.*

class FollowersAdapter() : RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    private val list: MutableList<UserFollowersItem> = mutableListOf()

    fun setData(list: MutableList<UserFollowersItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(users: UserFollowersItem){
            Glide.with(itemView)
                .load(users.avatar_url)
                .into(itemView.img_avatar_followers)
            itemView.tv_name_followers.text = users.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_followers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}