package com.example.submission_made.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submission_made.R

class MyPagerAdapter(var context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var fragmentList: ArrayList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }

    // Returns total number of pages
    override fun getCount(): Int {
        return fragmentList.size
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        when (position){
            0 -> return context.getString(R.string.movies)
            1 -> return context.getString(R.string.tv_shows)
            else -> return "Page $position"
        }
    }

}