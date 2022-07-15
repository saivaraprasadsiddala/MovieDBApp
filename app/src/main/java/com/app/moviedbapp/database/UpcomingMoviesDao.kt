package com.app.moviedbapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviedbapp.movies.popular.MovieItem
import com.app.moviedbapp.movies.upcoming.UpcomingMovieItem

@Dao
interface UpcomingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: UpcomingMovieItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<UpcomingMovieItem>): List<Long>

    @Query("SELECT * FROM UpcomingMovieItem ORDER BY timeStamp DESC")
    fun getMovies(): PagingSource<Int, UpcomingMovieItem>

    @Query("SELECT COUNT(id) from MovieItem")
    fun count(): Int
}