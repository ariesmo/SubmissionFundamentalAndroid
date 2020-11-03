package com.example.submissionfundamentaldicoding.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionfundamentaldicoding.R
import com.example.submissionfundamentaldicoding.adapter.FollowingAdapter
import com.example.submissionfundamentaldicoding.model.UserFollowing
import com.example.submissionfundamentaldicoding.model.UserFollowingItem
import com.example.submissionfundamentaldicoding.presenter.FollowingPresenter
import com.example.submissionfundamentaldicoding.presenter.InterfaceContractFollowing
import kotlinx.android.synthetic.main.fragment_following.*
import org.koin.android.ext.android.inject

class FollowingFragment : Fragment(), InterfaceContractFollowing.View {

    private val TAG = FollowingFragment::class.java.simpleName
    private val presenter by inject<FollowingPresenter>()
    private val adapter = FollowingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.view = this

        val sp = this.activity?.getSharedPreferences("git", Context.MODE_PRIVATE)
        val username = sp?.getString("nama", "")

        rv_following.layoutManager = LinearLayoutManager(activity)
        rv_following.adapter = adapter

        presenter.fetchGit(username!!)

    }

    override fun showLoading() {
        rv_following.visibility = View.GONE
        pb_following.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rv_following.visibility = View.VISIBLE
        pb_following.visibility = View.GONE
    }

    override fun showError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showUserGit(users: UserFollowing) {
        adapter.setData(users as MutableList<UserFollowingItem>)
    }

}