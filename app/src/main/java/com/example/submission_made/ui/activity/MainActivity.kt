package com.example.submission_made.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.example.submission_made.R
import com.example.submission_made.data.pojo.MovieData
import com.example.submission_made.databinding.ActivityMainBinding
import com.example.submission_made.ui.adapter.MyAdapter
import com.example.submission_made.ui.adapter.MyPagerAdapter
import com.example.submission_made.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var mContext: Context

    var dataList = ArrayList<MovieData>()
    lateinit var queue: RequestQueue
    lateinit var toolbarTop: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapterViewPager: FragmentPagerAdapter

    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vpPager = dataBinding.viewPager
        adapterViewPager = MyPagerAdapter(supportFragmentManager)
        vpPager.adapter = adapterViewPager

        toolbarTop = dataBinding.toolbar
        toolbarTop.title = "Movie List App"
        toolbarTop.subtitle = "Now playing movie list"
        setSupportActionBar(toolbarTop)
    }

//    @SuppressLint("NewApi")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        queue = Volley.newRequestQueue(this@MainActivity)
//
//        initViews()
//        setListeners()
//
//        linearLayoutManager = LinearLayoutManager(this)
//
//        Utility.populateMoviesData(dataList)
//        refreshRecyclerView(dataList.size)
//    }
//
//    fun initViews() {
//        toolbarTop = findViewById<Toolbar>(R.id.toolbar)
//        toolbarTop.title = "Movie List App"
//        toolbarTop.subtitle = "Now playing movie list"
//        setSupportActionBar(toolbarTop)
//
//        recyclerView = findViewById(R.id.mRecyclerView)
//        adapter = MyAdapter(this@MainActivity, applicationContext, dataList)
//        recyclerView.layoutManager = linearLayoutManager as RecyclerView.LayoutManager?
//        recyclerView.adapter = adapter
//    }
//
//    fun setListeners() {
//        adapter.setOnImageViewClickListener(object : MyAdapter.OnItemViewClickListener {
//            override fun onItemViewClick(imageView: ImageView, movieData: MovieData) {
//                val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
//
//                val transitionName = ViewCompat.getTransitionName(imageView)
//
//                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    this@MainActivity,
//                    imageView as View,
//                    transitionName!!
//                )
//
//                intent.putExtra("URL", movieData.getBackdropImageUrl())
//                intent.putExtra("TRANSITION_NAME", transitionName)
//                intent.putExtra("MOVIE_DATA", movieData)
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    startActivity(intent, options.toBundle())
//                } else {
//                    startActivity(intent)
//                }
//            }
//        })
//    }
//
//    fun refreshRecyclerView(listSize: Int) {
//        adapter.notifyItemRangeInserted(0, listSize)
//    }
//
//    fun resetRecyclerView(listSize: Int) {
//        dataList.clear()
//        adapter.notifyItemRangeRemoved(0, listSize)
//    }

}