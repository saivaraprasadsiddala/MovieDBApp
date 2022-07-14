package com.app.moviedbapp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.moviedbapp.R
import com.app.moviedbapp.database.MovieDataBase
import com.app.moviedbapp.movies.popular.MovieItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailFragment: BottomSheetDialogFragment() {

    companion object {

        fun newInstance(movieItem: MovieItem): MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("movie_model", movieItem)
                }
            }
        }
    }

    lateinit var movieItem: MovieItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieItem = arguments?.getParcelable<MovieItem>("movie_model")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.popularMovieImage)
        val movieTv = view.findViewById<TextView>(R.id.popularMovieTv)
        val dateTv = view.findViewById<TextView>(R.id.textView3)
        val descTv = view.findViewById<TextView>(R.id.textView2)
        val favoriteTv = view.findViewById<ImageView>(R.id.favoriteIv)
        movieItem.let {
            MovieItem.loadImage(imageView, it.posterPath)
            movieTv.text = it.title
            dateTv.text = it.releaseDate
            descTv.text = it.overview
            favoriteTv.setImageResource(if (it.favorite == 1) R.drawable.filled_star else R.drawable.borded_star)
        }

        favoriteTv.setOnClickListener {
            movieItem.favorite = if (movieItem.favorite == 1) 0 else 1
            CoroutineScope(Dispatchers.IO).launch {
                MovieDataBase.getInstance(requireContext())?.movieItemDao()?.insert(movieItem)
            }
            favoriteTv.setImageResource(if (movieItem.favorite == 1) R.drawable.filled_star else R.drawable.borded_star)
        }

    }
}