package com.app.moviedbapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.moviedbapp.database.MovieDataBase
import com.app.moviedbapp.mediator.MovieItemMediator
import com.app.moviedbapp.mediator.UpcomingMovieMediator
import com.app.moviedbapp.movies.popular.MovieItem
import com.app.moviedbapp.movies.upcoming.UpcomingMovieItem
import com.app.moviedbapp.network.RetrofitBuilder
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {

    var errorLiveData: MutableLiveData<String>? = null

    /**
     * This method will be used to pass PagingSource and remoteMediator to Pager
     *
     * @param context for accessing database.
     * @return Flow for observing data from database when data is updated.
     */
    fun getMovies(context: Context): Flow<PagingData<MovieItem>> {
        val pagingSource = { MovieDataBase.getInstance(context)?.movieItemDao()?.getMovies()!!}
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = pagingSource,
            remoteMediator = MovieItemMediator(RetrofitBuilder.moviesApiService, MovieDataBase.getInstance(context)!!).apply {
                errorLiveData = errorHandleLiveData
            }).flow
    }

    /**
     * This method will be used to pass PagingSource and remoteMediator to Pager
     *
     * @param context for accessing database.
     * @return Flow for observing data from database when data is updated.
     */
    fun getUpcomingMovies(context: Context): Flow<PagingData<UpcomingMovieItem>> {
        val pagingSource = { MovieDataBase.getInstance(context)?.upcomingMovieItemDao()?.getMovies()!!}
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = pagingSource,
            remoteMediator = UpcomingMovieMediator(RetrofitBuilder.moviesApiService, MovieDataBase.getInstance(context)!!).apply {
                errorLiveData = errorHandleLiveData
            }).flow
    }
}