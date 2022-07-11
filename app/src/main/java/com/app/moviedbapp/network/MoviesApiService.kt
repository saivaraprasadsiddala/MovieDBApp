package com.app.moviedbapp.network

import com.app.moviedbapp.movies.popular.MoviesResponse
import com.app.moviedbapp.movies.upcoming.UpcomingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface is to maintain all api calls at one place for retrofit.
 */
interface MoviesApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key")apiKey: String, @Query("page")page: Int): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key")apiKey: String, @Query("page")page: Int): UpcomingMoviesResponse
}