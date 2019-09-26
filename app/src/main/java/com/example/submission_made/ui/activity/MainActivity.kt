package com.example.submission_made.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.submission_made.R
import com.example.submission_made.databinding.ActivityMainBinding
import com.example.submission_made.ui.adapter.MyPagerAdapter
import com.example.submission_made.ui.base.BaseActivity
import com.example.submission_made.ui.fragment.MovieListFragment
import com.example.submission_made.ui.fragment.TvShowListFragment
import com.example.submission_made.viewmodel.MovieListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class MainActivity : BaseActivity<MovieListViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var mContext: Context
    lateinit var toolbarTop: Toolbar
    lateinit var adapterViewPager: MyPagerAdapter
    lateinit var tabLayout: TabLayout
    lateinit var fab: FloatingActionButton

    override val layoutRes: Int = R.layout.activity_main

    override fun getViewModel(): Class<MovieListViewModel> {
        return MovieListViewModel::class.java
    }

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

        fab = dataBinding.fab

        // val animatedVectorDrawableCompat:
        // AnimatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_likes)!!
        // fab.setImageDrawable(animatedVectorDrawableCompat)
        fab.setOnClickListener {

            val animatable = fab.drawable as Animatable
            if (animatable.isRunning)
                animatable.stop()
            else
                animatable.start()

            val handler = Handler()
            handler.postDelayed({

                val intent = Intent(this, MovieFavoritesActivity::class.java)
                startActivity(intent)

            }, 500)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var INSTANCE: MainActivity

        fun getInstance(): MainActivity {
            return INSTANCE
        }
    }

}