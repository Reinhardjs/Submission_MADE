package com.example.submission_made.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.submission_made.R
import com.example.submission_made.data.entity.FavoriteEntity
import com.example.submission_made.data.provider.Contract
import com.example.submission_made.databinding.ActivityMainBinding
import com.example.submission_made.ui.adapter.MyPagerAdapter
import com.example.submission_made.ui.base.BaseActivity
import com.example.submission_made.ui.fragment.MovieListFragment
import com.example.submission_made.ui.fragment.TvShowListFragment
import com.example.submission_made.utils.Utility
import com.example.submission_made.viewmodel.MovieListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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


        val work = Single.create<List<FavoriteEntity>> {

            val selectionArgs: Array<String> = arrayOf("-123", "movies")
            val projection = arrayOf(Contract.AUTHORITY)

            val cursor = contentResolver.query(
                Uri.parse(Contract.CONTENT_URI.toString()), projection, null,
                selectionArgs, null
            )

            if (cursor != null) {

                val movieList = Utility.favoriteCursorToListConverter(cursor)
                cursor.close()

                for (item in movieList){
                    Log.d("MYAPP", "MOVIE TITLE : " + item.title)
                }

                it.onSuccess(movieList)

            } else {

                it.onError(Throwable("Cursor Null"))

            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }

            R.id.action_reminder_settings -> {
                val intent = Intent(applicationContext, SettingActivity::class.java)
                startActivity(intent)
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