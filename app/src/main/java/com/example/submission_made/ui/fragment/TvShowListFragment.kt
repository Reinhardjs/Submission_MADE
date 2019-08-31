package com.example.submission_made.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_made.R
import com.example.submission_made.data.pojo.MovieData
import com.example.submission_made.data.remote.Status
import com.example.submission_made.databinding.FragmentListBinding
import com.example.submission_made.ui.activity.MovieDetailsActivity
import com.example.submission_made.ui.adapter.MyAdapter
import com.example.submission_made.ui.base.BaseFragment
import com.example.submission_made.ui.callbacks.ListCallback
import com.example.submission_made.viewmodel.MovieListViewModel
import javax.inject.Inject

class TvShowListFragment(var page: Int, var title: String) : BaseFragment<MovieListViewModel, FragmentListBinding>(), ListCallback {

    @Inject
    lateinit var mContext: Context

    public override val layoutRes: Int
        get() = R.layout.fragment_list

    public override fun getViewModel(): Class<MovieListViewModel> {
        return MovieListViewModel::class.java
    }

    override fun onItemClicked(imageView: ImageView, movieData: MovieData) {
        if (activity != null) {

            val intent = Intent(mContext, MovieDetailsActivity::class.java)
            val transitionName = ViewCompat.getTransitionName(imageView)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity as Activity,
                imageView as View,
                transitionName!!
            )

            intent.putExtra("URL", movieData.getBackdropImageUrl())
            intent.putExtra("TRANSITION_NAME", transitionName)
            intent.putExtra("MOVIE_DATA", movieData)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }

        }
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val adapter = MyAdapter(this)
        dataBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        dataBinding.recyclerView.setAdapter(adapter)

        return dataBinding.getRoot()
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getTvShows()?.observe(this, Observer { listResource ->

            if (listResource != null && (listResource.status === Status.ERROR || listResource.status === Status.SUCCESS)) {
                dataBinding.loginProgress.setVisibility(View.GONE)
            }

            dataBinding.setResource(listResource)

            Toast.makeText(mContext, "Data berhasil diload!", Toast.LENGTH_SHORT).show()
        })

    }

    companion object {
        @JvmStatic
        fun newInstance(page: Int, title: String): MovieListFragment {
            val fragment = MovieListFragment(page, title)
            return fragment
        }
    }

}