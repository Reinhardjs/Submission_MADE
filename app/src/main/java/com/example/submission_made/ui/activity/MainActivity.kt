package com.example.submission_made.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.example.submission_made.R
import com.example.submission_made.databinding.ActivityMainBinding
import com.example.submission_made.ui.adapter.MyPagerAdapter
import com.example.submission_made.ui.base.BaseActivity
import com.example.submission_made.ui.fragment.MovieListFragment
import com.example.submission_made.ui.fragment.TvShowListFragment
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var mContext: Context
    lateinit var toolbarTop: Toolbar
    lateinit var adapterViewPager: MyPagerAdapter
    lateinit var tabLayout: TabLayout

    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        INSTANCE = this

        val viewPager = dataBinding.viewPager
        toolbarTop = dataBinding.toolbar
        tabLayout = dataBinding.tabLayout

        toolbarTop.title = getString(R.string.toolbar_title)
        setSupportActionBar(toolbarTop)

        adapterViewPager = MyPagerAdapter(mContext, supportFragmentManager)

        adapterViewPager.addFragment(MovieListFragment.newInstance(0, "Page 1"))
        adapterViewPager.addFragment(TvShowListFragment.newInstance(1, "Page 2"))

        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = adapterViewPager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("MYAPP", "ON SAVE INSTANCE STATE on Activity")

        // Untuk penanganan data tetap, itu di dalam fragment :
        // MovieListFragment.kt dan TvShowListFragment.kt
        super.onSaveInstanceState(outState)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var INSTANCE: MainActivity

        fun getInstance(): MainActivity {
            return INSTANCE
        }
    }

}