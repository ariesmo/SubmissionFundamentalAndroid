package com.example.submissionfundamentaldicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.model.UserFollowingItem
import kotlinx.android.synthetic.main.item_following.view.*

class FollowingAdapter() : RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    private val list: MutableList<UserFollowingItem> = mutableListOf()

    fun setData(list: MutableList<UserFollowingItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(users: UserFollowingItem){
            Glide.with(itemView)
                .load(users.avatar_url)
                .into(itemView.img_avatar_following)
            itemView.tv_name_following.text = users.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_following, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}