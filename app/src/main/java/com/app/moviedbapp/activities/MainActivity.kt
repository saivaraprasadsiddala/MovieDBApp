package com.app.moviedbapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.app.moviedbapp.R
import com.app.moviedbapp.adapters.MovieListAdapter
import com.app.moviedbapp.adapters.OnItemClickListener
import com.app.moviedbapp.adapters.UpcomingListAdapter
import com.app.moviedbapp.databinding.ActivityMainBinding
import com.app.moviedbapp.modelviews.MainViewModel
import com.app.moviedbapp.movies.popular.MovieItem
import com.app.moviedbapp.movies.upcoming.UpcomingMovieItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        val popularMovieAdapter = MovieListAdapter()
        popularMovieAdapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(view: View, item: MovieItem) {
                MovieDetailFragment.newInstance(item).show(supportFragmentManager, "MovieDetailFragment")
            }

            override fun onUpcomingMovieItemClick(view: View, item: UpcomingMovieItem) {

            }

        }
        binding.popularMoviesList.adapter = popularMovieAdapter
        lifecycleScope.launch {
            viewModel.getMovies(baseContext)
                .collectLatest { movies ->
                    popularMovieAdapter.submitData(movies)
                }
        }

        val upcomingAdapter = UpcomingListAdapter()
        upcomingAdapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(view: View, item: MovieItem) {

            }

            override fun onUpcomingMovieItemClick(view: View, item: UpcomingMovieItem) {
             UpMovieDetailFragment.newInstance(item).show(supportFragmentManager, "UpMovieDetailFragment")
            }

        }
        binding.upcomingMoviesList.adapter = upcomingAdapter
        lifecycleScope.launch {
            viewModel.getUpcomingMovies(baseContext)
                .collectLatest { movies ->
                    upcomingAdapter.submitData(movies)
                }
        }
    }
}

