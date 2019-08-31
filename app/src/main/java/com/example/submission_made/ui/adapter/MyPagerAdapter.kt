package com.example.submission_made.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submission_made.ui.fragment.MovieListFragment
import com.example.submission_made.ui.fragment.TvShowListFragment

class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    // Returns total number of pages
    override fun getCount(): Int {
        return 2
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 // Fragment # 0 - This will show FirstFragment
            -> return MovieListFragment.newInstance(0, "Page 1")
            1 // Fragment # 0 - This will show FirstFragment different title
            -> return TvShowListFragment.newInstance(1, "Page 2")
            else -> return null
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

}