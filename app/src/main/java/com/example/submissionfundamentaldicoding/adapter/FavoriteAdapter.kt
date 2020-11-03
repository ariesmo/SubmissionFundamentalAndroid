package com.example.submissionfundamentaldicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.db.Favorite
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter() : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var listener : FavoriteInterface

    interface FavoriteInterface {
        fun deleteItem(users: Favorite, position: Int)
    }

    fun setOnCLickListener(listener: FavoriteInterface){
        this.listener = listener
    }

    private val list: MutableList<Favorite> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(users: Favorite, position: Int){
            Glide.with(itemView)
                .load(users.avatar)
                .into(itemView.img_avatar_favorite)
            itemView.tv_name_favorite.text = users.name
            listener.let {
                itemView.btn_delete.setOnClickListener {
                    listener.deleteItem(users, position)
                }
            }
        }
    }

    fun setData(item: MutableList<Favorite>){
        this.list.clear()
        this.list.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}