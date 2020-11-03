package com.example.submissionfundamentaldicoding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.submissionfundamentaldicoding.ui.fragment.FollowersFragment
import com.example.submissionfundamentaldicoding.ui.fragment.FollowingFragment

class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabName : Array<String> = arrayOf("Followers", "Following")

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FollowersFragment()
            1 -> FollowingFragment()
            else -> FollowersFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabName.get(position)

}