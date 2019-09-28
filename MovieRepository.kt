package com.example.submission_made.data.remote.repository

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.remote.Resource
import com.example.submission_made.data.remote.Status
import com.example.submission_made.utils.Utility
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class MovieRepository @Inject constructor() {

    fun loadMovies(): LiveData<Resource<ArrayList<MovieEntity>>>? {
        val liveEntity: MutableLiveData<Resource<ArrayList<MovieEntity>>> = MutableLiveData()

        val data = ArrayList<MovieEntity>()
        val resource = Resource(null, data, null)
        resource.status = Status.LOADING

        Timer().schedule(3000) {
            // Delay selama 3 detik

            val mainHandler = Handler(Looper.getMainLooper())
            mainHandler.post {

                Utility.populateMoviesData(data)

                resource.status = Status.SUCCESS
                liveEntity.value = resource
            }
        }

        return liveEntity
    }

    fun loadTvShows(): LiveData<Resource<ArrayList<MovieEntity>>>? {
        val liveEntity: MutableLiveData<Resource<ArrayList<MovieEntity>>> = MutableLiveData()

        val data = ArrayList<MovieEntity>()
        val resource = Resource(null, data, null)
        resource.status = Status.LOADING

        Timer().schedule(3000) {
            // Delay selama 3 detik

            val mainHandler = Handler(Looper.getMainLooper())
            mainHandler.post {

                Utility.populateTvShowData(data)

                resource.status = Status.SUCCESS
                liveEntity.value = resource
            }
        }

        return liveEntity
    }

}
