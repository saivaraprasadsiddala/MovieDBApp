package com.app.moviedbapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.moviedbapp.databinding.ItemPopularMovieBinding
import com.app.moviedbapp.movies.popular.MovieItem

class MovieListAdapter: PagingDataAdapter<MovieItem, MovieListAdapter.MovieViewHolder>(
    MovieComparator
) {
    var onItemClickListener: OnItemClickListener?=null

    inner class MovieViewHolder(var itemMovieListBinding: ItemPopularMovieBinding):
        RecyclerView.ViewHolder(itemMovieListBinding.root) {


        fun bindData(item: MovieItem) {
            itemMovieListBinding.movieItem = item
            itemMovieListBinding.onItemClick = onItemClickListener
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  MovieViewHolder (
        ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}