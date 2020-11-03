package com.example.submissionfundamentaldicoding.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.adapter.GitAdapter
import com.example.submissionfundamentaldicoding.model.UserResponse
import com.example.submissionfundamentaldicoding.model.UserResponseItem
import com.example.submissionfundamentaldicoding.presenter.InterfaceContractMain
import com.example.submissionfundamentaldicoding.presenter.MainPresenter
import com.example.submissionfundamentaldicoding.ui.fragment.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), InterfaceContractMain.View {

    private val TAG = MainActivity::class.java.simpleName
    private val presenter by inject<MainPresenter>()
    private val adapter = GitAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.view = this

        rv_git.layoutManager = LinearLayoutManager(this)
        rv_git.adapter = adapter

        presenter.fetchGit()

        adapter.setOnClickListener(object : GitAdapter.DetailInterface{
            override fun detailItem(users: UserResponseItem, position: Int) {

                val bundle = Bundle()
                bundle.putInt("id", users.id)
                bundle.putString("nama", users.login)
                bundle.putString("avatar", users.avatar_url)

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }

    override fun showLoading() {
        rv_git.visibility = View.GONE
        pb_git.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rv_git.visibility = View.VISIBLE
        pb_git.visibility = View.GONE
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showUserGit(users: UserResponse) {
        Log.d(TAG, "fetch adapter")
        adapter.setData(users as MutableList<UserResponseItem>)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_setting -> {
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_favorite -> {
                Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun toFragment(){
        supportFragmentManager.beginTransaction()
    }
}