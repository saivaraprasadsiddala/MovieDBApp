package com.app.moviedbapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.moviedbapp.databinding.ItemUpcomingMovieBinding
import com.app.moviedbapp.movies.upcoming.UpcomingMovieItem

class UpcomingListAdapter: PagingDataAdapter<UpcomingMovieItem, UpcomingListAdapter.MovieViewHolder>(
    MovieComparator
) {
    var onItemClickListener: OnItemClickListener?=null

    inner class MovieViewHolder(var itemMovieListBinding: ItemUpcomingMovieBinding):
        RecyclerView.ViewHolder(itemMovieListBinding.root) {


        fun bindData(item: UpcomingMovieItem) {
            itemMovieListBinding.movieItem = item
            itemMovieListBinding.onItemClick = onItemClickListener
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<UpcomingMovieItem>() {
        override fun areItemsTheSame(oldItem: UpcomingMovieItem, newItem: UpcomingMovieItem): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UpcomingMovieItem, newItem: UpcomingMovieItem): Boolean {
            return oldItem == newItem
        }
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  MovieViewHolder (
        ItemUpcomingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}