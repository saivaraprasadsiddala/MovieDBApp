package com.app.moviedbapp.movies.upcoming

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UpcomingMovieItem(

	@field:SerializedName("overview")
	var overview: String? = null,

	@field:SerializedName("original_language")
	var originalLanguage: String? = null,

	@field:SerializedName("original_title")
	var originalTitle: String? = null,

	@field:SerializedName("video")
	var video: Boolean? = null,

	@field:SerializedName("title")
	var title: String? = null,

	@field:SerializedName("poster_path")
	var posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	var backdropPath: String? = null,

	@field:SerializedName("release_date")
	var releaseDate: String? = null,

	@field:SerializedName("popularity")
	var popularity: Double? = null,

	@field:SerializedName("vote_average")
	var voteAverage: Double? = null,

	@PrimaryKey
	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("adult")
	var adult: Boolean? = null,

	@field:SerializedName("vote_count")
	var voteCount: Int? = null,

	@field:SerializedName("time_stamp")
	var timeStamp: Int? = null,

) : Parcelable