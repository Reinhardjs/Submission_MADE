package com.example.submission_made.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.submission_made.R
import com.example.submission_made.utils.Utility
import com.example.submission_made.data.pojo.MovieData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var movieData: MovieData
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportPostponeEnterTransition()

        queue = Volley.newRequestQueue(this)

        trailer3url = ""
        trailer2url = trailer3url
        trailer1url = trailer2url
        review3url = trailer1url
        review2url = review3url
        review1url = review2url

        val bundle = intent.extras
        movieData = bundle.getParcelable("MOVIE_DATA")

        val toolbarTop = findViewById<View>(R.id.toolbar) as Toolbar
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

        review1.setOnClickListener(mReviewClickListener)
        review2.setOnClickListener(mReviewClickListener)
        review3.setOnClickListener(mReviewClickListener)

        trailer1.setOnClickListener(mTrailerClickListener)
        trailer2.setOnClickListener(mTrailerClickListener)
        trailer3.setOnClickListener(mTrailerClickListener)

        Picasso.with(applicationContext)
            .load(movieData.getPosterImageUrl())
            .into(posterImage)

        requestReview()
        requestTrailer()

        val ratingPercent = movieData.vote_average
        val ratingCount = movieData.vote_count
        rating.text = "$ratingPercent/10 ($ratingCount)"
        overview.text = movieData.overview
        releaseDate.text = movieData.release_date

        val fabFavorite = findViewById<FloatingActionButton>(R.id.fabFavorite)

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

    }

    fun requestReview() {
        val url = "https://api.themoviedb.org/3/movie/" +
                movieData.id +
                "/reviews?" +
                "api_key=b2e10e07882544ff0655c3a7fe130806&language=en-US&page=1"

        val getRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                // display response
                // Log.d("Response", response.toString());

                try {
                    val jsonObject = JSONObject(response.toString())
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
            Response.ErrorListener { }
        )

        queue.add(getRequest)
    }

    fun requestTrailer() {
        val url = "https://api.themoviedb.org/3/movie/" +
                movieData.id +
                "/videos?" +
                "api_key=b2e10e07882544ff0655c3a7fe130806&language=en-US"

        val getRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                // display response
                // Log.d("Response", response.toString());

                try {
                    val jsonObject = JSONObject(response.toString())
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
            Response.ErrorListener { }
        )

        queue.add(getRequest)
    }

}
