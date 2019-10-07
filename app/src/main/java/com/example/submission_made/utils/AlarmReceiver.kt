package com.example.submission_made.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.submission_made.R
import com.example.submission_made.data.Repo
import com.example.submission_made.data.Repository
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.ui.activity.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    private var notifId: Int = 0
    private var mRepo: Repo = Repository.of()

    lateinit var listMovies: List<MovieEntity>

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(EXTRA_TYPE)

        Log.d("MYAPP", "ON ALARM START")

        if (type.equals(TYPE_RELEASEREMINDER)) {
            notifId = ID_RELEASEREMINDER
            checkNewReleaseMovies(object : ReleaseMovieCallbacks {
                override fun onSuccess(movies: List<MovieEntity>) {
                    listMovies = movies
                    for (i in listMovies.indices) {
                        showAlarmNotification(
                            context,
                            listMovies[i].title,
                            listMovies[i].title!! + " " + context.resources.getString(R.string.new_released_text),
                            notifId++
                        )
                    }
                }

                override fun onFailure(failure: Boolean) {
                    if (failure) {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        } else if (type.equals(TYPE_DAILYREMINDER)) {
            val message = intent.getStringExtra(EXTRA_MESSAGE)
            val title = "Daily Reminder"
            notifId = ID_DAILYREMINDER
            showAlarmNotification(context, title, message, notifId)
        }

    }

    private fun showAlarmNotification(
        context: Context,
        title: String?,
        message: String,
        notifId: Int
    ) {

        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "AlarmManager channel"

        val intent: Intent
        intent = Intent(context.applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_movie_black_24dp)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManagerCompat.notify(notifId, notification)
    }

    fun setRepeatingAlarmDaily(context: Context, isChecked: Boolean) {
        if (isChecked) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra(EXTRA_TYPE, TYPE_DAILYREMINDER)
            intent.putExtra(EXTRA_MESSAGE, context.resources.getString(R.string.content_text))

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 7)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            val pendingIntent = PendingIntent.getBroadcast(context, ID_DAILYREMINDER, intent, 0)
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else {
            cancelAlarm(context, TYPE_DAILYREMINDER)
        }
    }

    fun setRepeatingAlarmRelease(context: Context, isChecked: Boolean) {
        if (isChecked) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra(EXTRA_TYPE, TYPE_RELEASEREMINDER)

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 8)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            val pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASEREMINDER, intent, 0)

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else {
            cancelAlarm(context, TYPE_RELEASEREMINDER)
        }
    }

    fun cancelAlarm(context: Context, type: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = if (type.equals(TYPE_RELEASEREMINDER, ignoreCase = true))
            ID_RELEASEREMINDER else ID_DAILYREMINDER
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        if (type.equals(TYPE_DAILYREMINDER, ignoreCase = true)) {
            Toast.makeText(context, "Daily Reminder Off", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Release Reminder Off", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkNewReleaseMovies(callbacks: ReleaseMovieCallbacks) {

        val date = Calendar.getInstance().time
        @SuppressLint("SimpleDateFormat") val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val todaydate = simpleDateFormat.format(date)

        val disposable = mRepo.api.getTodayReleases(todaydate, todaydate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieList ->

                    for (movieItem in movieList) {
                        Log.d("MYAPP", "TITLE : " + movieItem.title!!)
                    }

                    callbacks.onSuccess(movieList)

                },
                { error ->

                    callbacks.onFailure(true)

                }
            )
    }


    interface ReleaseMovieCallbacks {
        fun onSuccess(movies: List<MovieEntity>)

        fun onFailure(failure: Boolean)
    }

    companion object {

        val TYPE_RELEASEREMINDER = "ReleaseReminder"
        val TYPE_DAILYREMINDER = "DailyReminder"
        private val EXTRA_MESSAGE = "message"
        private val EXTRA_TYPE = "type"

        private val ID_DAILYREMINDER = 100
        private val ID_RELEASEREMINDER = 101
    }


}