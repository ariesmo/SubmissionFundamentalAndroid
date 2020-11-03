package com.example.submissionfundamentaldicoding.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.adapter.FollowersAdapter
import com.example.submissionfundamentaldicoding.model.UserFollowers
import com.example.submissionfundamentaldicoding.model.UserFollowersItem
import com.example.submissionfundamentaldicoding.presenter.FollowersPresenter
import com.example.submissionfundamentaldicoding.presenter.InterfaceContractFollowers
import kotlinx.android.synthetic.main.fragment_followers.*
import org.koin.android.ext.android.inject


class FollowersFragment : Fragment(), InterfaceContractFollowers.View {

    private val TAG = FollowersFragment::class.java.simpleName
    private val presenter by inject<FollowersPresenter>()
    private val adapter = FollowersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        Log.d(TAG, "FollowersFragment: Started")

        val sp  = this.activity?.getSharedPreferences("git", Context.MODE_PRIVATE)
        val username = sp?.getString("nama", "")

        Log.d(TAG, username.toString())

        rv_followers.layoutManager = LinearLayoutManager(activity)
        rv_followers.adapter = adapter

        presenter.fetchGit(username!!)

    }

    override fun showLoading() {
        rv_followers.visibility = View.GONE
        pb_followers.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rv_followers.visibility = View.VISIBLE
        pb_followers.visibility = View.GONE
    }

    override fun showError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showUserGit(users: UserFollowers) {
        adapter.setData(users as MutableList<UserFollowersItem>)
    }

}