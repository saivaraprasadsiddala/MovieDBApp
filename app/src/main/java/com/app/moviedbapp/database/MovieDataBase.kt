package com.app.moviedbapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.moviedbapp.movies.popular.MovieItem
import com.app.moviedbapp.movies.popular.RemoteKeys
import com.app.moviedbapp.movies.upcoming.UpcomingMovieItem
import com.app.moviedbapp.movies.upcoming.UpcomingRemoteKeys


@Database(entities = [MovieItem::class, UpcomingMovieItem::class, RemoteKeys::class, UpcomingRemoteKeys::class], version = 3, exportSchema = false)
abstract class MovieDataBase: RoomDatabase()  {
    abstract fun movieItemDao():MovieDao
    abstract fun remoteKeyDao(): RemoteKeysDao
    abstract fun upcomingKeyDao(): UpcomingRemoteKeysDao
    abstract fun upcomingMovieItemDao(): UpcomingMoviesDao

    companion object  {
        private const val DATABASE_NAME = "MovieDataBase.db"
        var INSTANCE: MovieDataBase? = null
        fun getInstance(context: Context): MovieDataBase? {
            if (INSTANCE == null) {
                synchronized(MovieDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context, MovieDataBase::class.java, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}