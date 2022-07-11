package com.app.moviedbapp.adapters

import android.view.View
import com.app.moviedbapp.movies.popular.MovieItem
import com.app.moviedbapp.movies.upcoming.UpcomingMovieItem

interface OnItemClickListener {
    fun onItemClick(view: View, item: MovieItem)
    fun onUpcomingMovieItemClick(view: View, item: UpcomingMovieItem)
}