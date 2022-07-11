package com.app.moviedbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.app.moviedbapp.adapters.MovieListAdapter
import com.app.moviedbapp.adapters.UpcomingListAdapter
import com.app.moviedbapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val popularMovieAdapter = MovieListAdapter()
        binding.popularMoviesList.adapter = popularMovieAdapter
        lifecycleScope.launch {
            viewModel.getMovies(baseContext)
                .collectLatest { movies ->
                    popularMovieAdapter.submitData(movies)
                }
        }

        val upcomingAdapter = UpcomingListAdapter()
        binding.upcomingMoviesList.adapter = upcomingAdapter
        lifecycleScope.launch {
            viewModel.getUpcomingMovies(baseContext)
                .collectLatest { movies ->
                    upcomingAdapter.submitData(movies)
                }
        }
    }
}