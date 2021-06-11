package com.medialink.keloladatasubmission.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity

@Database(
    entities = [MovieDetailEntity::class, TvDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TheMovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: TheMovieDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): TheMovieDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TheMovieDatabase::class.java,
                    "themovie.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}