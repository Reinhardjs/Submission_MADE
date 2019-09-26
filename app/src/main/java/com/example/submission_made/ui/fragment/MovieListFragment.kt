package com.example.submission_made.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_made.R
import com.example.submission_made.data.entity.BaseEntity
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.remote.Resource
import com.example.submission_made.data.remote.Status
import com.example.submission_made.databinding.FragmentListBinding
import com.example.submission_made.ui.activity.MovieDetailsActivity
import com.example.submission_made.ui.adapter.MovieAdapter
import com.example.submission_made.ui.base.BaseFragment
import com.example.submission_made.ui.callbacks.ListCallback
import com.example.submission_made.viewmodel.MovieListViewModel
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListFragment(var page: Int, var title: String) :
    BaseFragment<MovieListViewModel, FragmentListBinding>(),
    ListCallback<MovieEntity> {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var gson: Gson

    lateinit var resource: Resource<List<MovieEntity>>
    lateinit var adapter: MovieAdapter

    constructor() : this(0, "")

    public override val layoutRes: Int
        get() = R.layout.fragment_list

    public override fun getViewModel(): Class<MovieListViewModel> {
        return MovieListViewModel::class.java
    }

    override fun onItemClicked(imageView: ImageView, movieEntity: MovieEntity) {
        if (activity != null) {

            val intent = Intent(mContext, MovieDetailsActivity::class.java)
            val transitionName = ViewCompat.getTransitionName(imageView)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity as Activity,
                imageView as View,
                transitionName!!
            )

            intent.putExtra("URL", movieEntity.getBackdropImageUrl())
            intent.putExtra("TRANSITION_NAME", transitionName)
            intent.putExtra("MOVIE_DATA", movieEntity as BaseEntity)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("MYAPP", "ON SAVE INSTANCE STATE on Fragment")
        outState.putSerializable("resource", resource)
        super.onSaveInstanceState(outState)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        adapter = MovieAdapter(this)
        dataBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        dataBinding.recyclerView.adapter = adapter

        return dataBinding.root
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState == null) {

            var disposable: Disposable? =
                viewModel.getMovies()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {

                            // Log.d("MYAPP", "ON RESPONSE, SIZE : " + it.results.size)

                            Log.d("MYAPP", "getAll UserEntity size - ${it.size}")

                            if (it != null) {
                                dataBinding.loginProgress.visibility = View.GONE
                            }

                            val resource = Resource(null, it, null)
                            resource.status = Status.LOADING

                            dataBinding.resource = resource
                            this.resource = resource

                            Toast.makeText(
                                mContext,
                                getString(R.string.data_load_success),
                                Toast.LENGTH_SHORT
                            ).show()

                        },
                        { it.printStackTrace() }
                    )

        } else {

            resource = savedInstanceState.getSerializable("resource") as Resource<List<MovieEntity>>
            dataBinding.loginProgress.visibility = View.GONE
            dataBinding.resource = resource

        }

    }

    companion object {
        @JvmStatic
        fun newInstance(page: Int, title: String): MovieListFragment {
            val fragment = MovieListFragment(page, title)
            return fragment
        }
    }

}