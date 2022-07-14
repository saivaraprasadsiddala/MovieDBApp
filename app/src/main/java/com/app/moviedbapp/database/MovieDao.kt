package com.app.moviedbapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviedbapp.movies.popular.MovieItem

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieItem>): List<Long>

    @Query("SELECT * FROM MovieItem ORDER BY timeStamp DESC")
    fun getMovies(): PagingSource<Int, MovieItem>

    @Query("SELECT COUNT(id) from MovieItem")
    fun count(): Int
}