package com.example.submissionfundamentaldicoding.ui.activity

import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.adapter.FavoriteAdapter
import com.example.submissionfundamentaldicoding.presenter.FavoritePresenter
import com.example.submissionfundamentaldicoding.presenter.InterfaceContractFavorite
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FavoriteActivity : AppCompatActivity(), InterfaceContractFavorite.View {

    private val TAG = FavoriteActivity::class.java.simpleName
    private val presenter by inject<FavoritePresenter>()
    private val adapter = FavoriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        presenter.view = this

        rv_favorite.setHasFixedSize(true)
        rv_favorite.layoutManager = LinearLayoutManager(this)
        rv_favorite.adapter = adapter

        GlobalScope.launch(Dispatchers.Main){
            val favorite = presenter.getAllData()

            runOnUiThread {
                if (favorite != null){
                    rv_favorite.visibility = View.VISIBLE
                    tv_nodata.visibility = View.GONE
                    adapter.setData(favorite)
                } else {
                    rv_favorite.visibility = View.GONE
                    tv_nodata.visibility = View.VISIBLE
                }
            }
        }

        adapter.setOnCLickListener(object : FavoriteAdapter.FavoriteInterface{
            override fun deleteItem(users: Int?, position: Int) {
                presenter.delete(users)
                presenter.getAllData()
            }
        })

    }

    override fun onSuccess(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showData(favorite: Cursor) {
        adapter.setData(favorite)
    }
}