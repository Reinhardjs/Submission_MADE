package com.example.submission_made.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.submission_made.R
import com.example.submission_made.databinding.ActivityMovieFavoritesBinding
import com.example.submission_made.ui.adapter.MyPagerAdapter
import com.example.submission_made.ui.base.BaseActivity
import com.example.submission_made.ui.fragment.FavoriteMovieListFragment
import com.example.submission_made.ui.fragment.FavoriteTvShowListFragment
import com.example.submission_made.viewmodel.MovieListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class MovieFavoritesActivity : BaseActivity<MovieListViewModel, ActivityMovieFavoritesBinding>() {

    @Inject
    lateinit var mContext: Context
    lateinit var toolbarTop: Toolbar
    lateinit var adapterViewPager: MyPagerAdapter
    lateinit var tabLayout: TabLayout
    lateinit var fab: FloatingActionButton

    override val layoutRes: Int = R.layout.activity_movie_favorites

    override fun getViewModel(): Class<MovieListViewModel> {
        return MovieListViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        INSTANCE = this

        val viewPager = dataBinding.viewPager
        toolbarTop = dataBinding.toolbar
        tabLayout = dataBinding.tabLayout

        toolbarTop.title = getString(R.string.favorite_toolbar_title)
        setSupportActionBar(toolbarTop)

        adapterViewPager = MyPagerAdapter(mContext, supportFragmentManager)

        adapterViewPager.addFragment(FavoriteMovieListFragment.newInstance(0, "Page 1"))
        adapterViewPager.addFragment(FavoriteTvShowListFragment.newInstance(1, "Page 2"))

        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = adapterViewPager

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

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var INSTANCE: MovieFavoritesActivity

        fun getInstance(): MovieFavoritesActivity {
            return INSTANCE
        }
    }

}
