package com.example.submissionfundamentaldicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.model.UserResponseItem
import kotlinx.android.synthetic.main.item_user.view.*

class GitAdapter() : RecyclerView.Adapter<GitAdapter.ViewHolder>() {

    private lateinit var listener: DetailInterface

    interface DetailInterface {
        fun detailItem(users: UserResponseItem, position: Int)
    }

    fun setOnClickListener(listener: DetailInterface){
        this.listener = listener
    }

    private var list: MutableList<UserResponseItem> = mutableListOf()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(users: UserResponseItem, position: Int){
            Glide.with(itemView)
                .load(users.avatar_url)
                .into(itemView.avatar)
            itemView.tv_username.text = users.login
            listener.let {
                itemView.ll_main.setOnClickListener {
                    listener.detailItem(users, position)
                }
            }
        }
    }

    fun setData(list: MutableList<UserResponseItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}