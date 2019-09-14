package com.example.submission_made.data

import android.content.Context
import androidx.room.*
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.local.MovieDao
import java.util.*

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {

        private const val DB_NAME = "testapp.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}

class DateConverter {

    @TypeConverter
    fun toDate(value: Long?) = value?.let { Date(value) }

    @TypeConverter
    fun toLong(value: Date?) = value?.time
}