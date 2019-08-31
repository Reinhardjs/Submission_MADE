package com.example.submission_made.data.remote.repository

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission_made.data.pojo.MovieData
import com.example.submission_made.data.remote.Resource
import com.example.submission_made.data.remote.Status
import com.example.submission_made.utils.Utility
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class MovieRepository @Inject constructor() {

    fun loadMovies(): LiveData<Resource<ArrayList<MovieData>>>? {
        val liveData: MutableLiveData<Resource<ArrayList<MovieData>>> = MutableLiveData()

        val data = ArrayList<MovieData>()
        val resource = Resource(null, data, null)
        resource.status = Status.LOADING

        Timer().schedule(3000) {
            // Delay selama 3 detik

            val mainHandler = Handler(Looper.getMainLooper())
            mainHandler.post {

                Utility.populateMoviesData(data)

                resource.status = Status.SUCCESS
                liveData.value = resource
            }
        }

        return liveData
    }

    fun loadTvShows(): LiveData<Resource<ArrayList<MovieData>>>? {
        val liveData: MutableLiveData<Resource<ArrayList<MovieData>>> = MutableLiveData()

        val data = ArrayList<MovieData>()
        val resource = Resource(null, data, null)
        resource.status = Status.LOADING

        Timer().schedule(3000) {
            // Delay selama 3 detik

            val mainHandler = Handler(Looper.getMainLooper())
            mainHandler.post {

                Utility.populateTvShowData(data)

                resource.status = Status.SUCCESS
                liveData.value = resource
            }
        }

        return liveData
    }

}
