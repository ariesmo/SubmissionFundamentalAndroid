package com.example.submissionfundamentaldicoding.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.adapter.ViewPagerAdapter
import com.example.submissionfundamentaldicoding.db.Favorite
import com.example.submissionfundamentaldicoding.model.UserResponseItem
import com.example.submissionfundamentaldicoding.presenter.DetailPresenter
import com.example.submissionfundamentaldicoding.presenter.FavoritePresenter
import com.example.submissionfundamentaldicoding.presenter.InterfaceContractDetail
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity(), InterfaceContractDetail.View {

    private val TAG = DetailActivity::class.java.simpleName
    private val presenter by inject<DetailPresenter>()
    private val presenterFavorite by inject<FavoritePresenter>()

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.view = this

        val user = intent.extras
        val id = user?.getInt("id")
        val username = user?.getString("nama")
        val avatar = user?.getString("avatar")

        val sp = getSharedPreferences("git", Context.MODE_PRIVATE)
        val esp = sp.edit()
        esp.putString("nama", username)
        esp.commit()

        presenter.fetchGit(username.toString())

        val sps = getSharedPreferences("detail", Context.MODE_PRIVATE)
        val id_user = sps.getInt("id", 0)
        Log.d(TAG, "id_user"+id_user.toString())

        Log.d(TAG, "id_main"+id)

        val fav = presenterFavorite.getAllData()
        fav.forEach {
            val idFav = it.id
            Log.d(TAG, "idFav"+idFav.toString())

            if (id_user == idFav){
                isFavorite = true
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_favorite))
            } else {
                isFavorite = false
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_favorite_border))
            }
        }

        if (id_user == id){
            isFavorite = true
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_favorite))
        } else {
            isFavorite = false
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_favorite_border))
        }

        val adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter

        tabs.setupWithViewPager(view_pager)

        Log.d(TAG, isFavorite.toString())

        floatingActionButton.setOnClickListener {
            if (isFavorite){
                Toast.makeText(this, "You've been added favorite this git account", Toast.LENGTH_SHORT).show()
            } else {
                GlobalScope.launch {
                    val favorite = Favorite(
                        id = id,
                        name = username,
                        avatar = avatar
                    )
                    Log.d(TAG, "id_to_favorite"+favorite.id.toString())
                    Log.d(TAG, "name_to_favorite"+favorite.name)
                    Log.d(TAG, "avatar_to_favorite"+favorite.avatar!!)
                    presenterFavorite.insert(favorite)
                }
                Log.d(TAG, isFavorite.toString())
                isFavorite = true
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_favorite))
            }
        }
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showUserGit(users: UserResponseItem) {
        Glide.with(this)
            .load(users.avatar_url)
            .into(avatar_url)
        tv_name.text = users.login
    }
}