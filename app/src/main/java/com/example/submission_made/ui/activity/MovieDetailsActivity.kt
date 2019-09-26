package com.example.submission_made.ui.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.submission_made.R
import com.example.submission_made.data.entity.BaseEntity
import com.example.submission_made.data.entity.EntityConverter
import com.example.submission_made.databinding.ActivityMovieDetailsBinding
import com.example.submission_made.ui.base.BaseActivity
import com.example.submission_made.utils.Utility
import com.example.submission_made.viewmodel.MovieDetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject

class MovieDetailsActivity : BaseActivity<MovieDetailViewModel, ActivityMovieDetailsBinding>() {

    lateinit var disposable1: Disposable

    lateinit var fabFavorite: FloatingActionButton
    lateinit var toolbarTop: Toolbar
    lateinit var movieData: BaseEntity
    lateinit var queue: RequestQueue
    lateinit var rating: TextView
    lateinit var releaseDate: TextView
    lateinit var overview: TextView
    lateinit var review1: TextView
    lateinit var review2: TextView
    lateinit var review3: TextView
    lateinit var trailer1: TextView
    lateinit var trailer2: TextView
    lateinit var trailer3: TextView
    lateinit var review1url: String
    lateinit var review2url: String
    lateinit var review3url: String
    lateinit var trailer1url: String
    lateinit var trailer2url: String
    lateinit var trailer3url: String

    lateinit var posterImage: ImageView
    private var isChecked: Boolean = false

    override val layoutRes: Int = R.layout.activity_movie_details

    private val mReviewClickListener = View.OnClickListener { v ->
        var url: String? = ""

        if (v === review1) {
            url = review1url
        } else if (v === review2) {
            url = review2url
        } else if (v === review3) {
            url = review3url
        }
        if (url != null && !url.isEmpty()) {
            Utility.viewUrl(url, applicationContext)
        }
    }

    // handle clicks on Trailers - view the Youtube URL

    private val mTrailerClickListener = View.OnClickListener { v ->
        var url: String? = ""

        if (v === trailer1) {
            url = trailer1url
        } else if (v === trailer2) {
            url = trailer2url
        } else if (v === trailer3) {
            url = trailer3url
        }
        if (url != null && !url.isEmpty()) {
            Utility.watchYoutubeVideo(url, applicationContext)
        }
    }

    override fun getViewModel(): Class<MovieDetailViewModel> {
        return MovieDetailViewModel::class.java
    }

    override fun onStop() {
        super.onStop()

        if (disposable1 != null && !disposable1.isDisposed) {
            disposable1.dispose()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        queue = Volley.newRequestQueue(this)

        trailer3url = ""
        trailer2url = trailer3url
        trailer1url = trailer2url
        review3url = trailer1url
        review2url = review3url
        review1url = review2url

        val bundle = intent.extras
        movieData = bundle.getSerializable("MOVIE_DATA") as BaseEntity

        initViews()
        initListener()

        Picasso.with(applicationContext)
            .load(movieData.getPosterImageUrl())
            .into(posterImage)

        val ratingPercent = movieData.vote_average
        val ratingCount = movieData.vote_count
        rating.text = "$ratingPercent/10 ($ratingCount)"
        overview.text = movieData.overview
        releaseDate.text = movieData.release_date

        val url = bundle.getString("URL")
        val transitionName = bundle.getString("TRANSITION_NAME")
        val imageView = findViewById<ImageView>(R.id.imagePoster)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.transitionName = transitionName
        }

        Picasso.with(applicationContext)
            .load(url)
            .noFade()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    supportStartPostponedEnterTransition()
                }

                override fun onError() {
                    supportStartPostponedEnterTransition()
                }
            })

        requestReview()
        requestTrailer()
    }

    fun initViews() {
        fabFavorite = findViewById(R.id.fabFavorite)
        toolbarTop = findViewById<View>(R.id.toolbar) as Toolbar
        toolbarTop.title = movieData.title
        setSupportActionBar(toolbarTop)

        rating = findViewById(R.id.rating)
        releaseDate = findViewById(R.id.releaseDate)
        overview = findViewById(R.id.overview)
        trailer1 = findViewById(R.id.trailer1)
        trailer2 = findViewById(R.id.trailer2)
        trailer3 = findViewById(R.id.trailer3)
        review1 = findViewById(R.id.review1)
        review2 = findViewById(R.id.review2)
        review3 = findViewById(R.id.review3)
        posterImage = findViewById(R.id.posterImage)
    }

    fun initListener() {


        fabFavorite.setOnClickListener {

            isChecked = !isChecked
            val stateSet = intArrayOf(android.R.attr.state_checked * if (isChecked) 1 else -1)
            fabFavorite.setImageState(stateSet, true)

            if (isChecked) {

                val disposable: Disposable = Completable.fromAction {
                    viewModel.insertFavorite(EntityConverter.convertToFavoriteEntity(movieData))
                }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.single())
                    .subscribe {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.favorite_inserted),
                            Toast.LENGTH_LONG
                        ).show()
                    }

            } else {

                val disposable: Disposable = Completable.fromAction {
                    viewModel.deleteFavorite(movieData.id)
                }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.single())
                    .subscribe {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.favorite_deleted),
                            Toast.LENGTH_LONG
                        ).show()
                    }

            }
        }


        val handler = Handler()
        handler.postDelayed({
            disposable1 = viewModel.getFavorite(movieData.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it != null) {
                            isChecked = true
                            val stateSet =
                                intArrayOf(android.R.attr.state_checked * if (isChecked) 1 else -1)
                            fabFavorite.setImageState(stateSet, true)
                        }
                    },
                    {
                        it.printStackTrace()
                        Log.d("MYAPP", "Exception for get favorite item : " + it.message)
                    }
                )
        }, 500)

        review1.setOnClickListener(mReviewClickListener)
        review2.setOnClickListener(mReviewClickListener)
        review3.setOnClickListener(mReviewClickListener)

        trailer1.setOnClickListener(mTrailerClickListener)
        trailer2.setOnClickListener(mTrailerClickListener)
        trailer3.setOnClickListener(mTrailerClickListener)
    }

    fun requestReview() {

        val disposable: Disposable = viewModel.getReviews(movieData.id, queue)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {

                    try {
                        val jsonObject = JSONObject(it.toString())
                        val results = jsonObject.getJSONArray("results")

                        for (i in 0 until results.length()) {
                            if (i == 3) {
                                break
                            }

                            val result = results.getJSONObject(i)
                            when (i) {
                                0 -> {
                                    review1url = result.getString("url")
                                    review1.visibility = View.VISIBLE
                                }
                                1 -> {
                                    review2url = result.getString("url")
                                    review2.visibility = View.VISIBLE
                                }
                                2 -> {
                                    review3url = result.getString("url")
                                    review3.visibility = View.VISIBLE
                                }
                            }
                        }

                    } catch (ignored: Exception) {

                    }

                },
                {

                    it.printStackTrace()

                }
            )
    }

    fun requestTrailer() {

        val disposable: Disposable = viewModel.getTrailers(movieData.id, queue)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {

                    try {
                        val jsonObject = JSONObject(it.toString())
                        val results = jsonObject.getJSONArray("results")

                        for (i in 0 until results.length()) {
                            if (i == 3) {
                                break
                            }

                            val result = results.getJSONObject(i)
                            when (i) {
                                0 -> {
                                    trailer1url = result.getString("key")
                                    trailer1.visibility = View.VISIBLE
                                }
                                1 -> {
                                    trailer2url = result.getString("key")
                                    trailer2.visibility = View.VISIBLE
                                }
                                2 -> {
                                    trailer3url = result.getString("key")
                                    trailer3.visibility = View.VISIBLE
                                }
                            }
                        }

                    } catch (ignored: JSONException) {

                    }

                },
                {

                    it.printStackTrace()

                }
            )

    }

}
