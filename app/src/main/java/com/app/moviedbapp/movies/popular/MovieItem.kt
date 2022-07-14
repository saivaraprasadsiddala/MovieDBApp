package com.app.moviedbapp.movies.popular


import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.moviedbapp.R
import com.app.moviedbapp.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * This class is used to access data from server and store data in local database using room
 */
@Parcelize
@Entity
data class MovieItem(

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

    var timeStamp: Long? = null,

    var favorite:Int?= null
) : Parcelable {
    companion object {

        /**
         * This method is used for loading image from server to imageview using glide library.
         * Image is center cropped for better display
         */
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(view: ImageView, posterPath: String?) {
            view.transitionName = posterPath
            Glide.with(view.context)
                .load(BuildConfig.IMAGE_URL + posterPath)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_movie)
                        .centerCrop()
                        .useUnlimitedSourceGeneratorsPool(true)
                )
                .into(view)
        }

        /**
         * This method is used for loading image from server to imageview using glide library.
         * Image is optionally fit to center for better display
         */
        @JvmStatic
        @BindingAdapter("app:imageFullUrl")
        fun loadFullImage(view: ImageView, posterPath: String?) {
            view.transitionName = posterPath
            Glide.with(view.context)
                .load(BuildConfig.IMAGE_URL + posterPath)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_movie)
                        .fitCenter()
                        .useUnlimitedSourceGeneratorsPool(true)
                )
                .into(view)
        }
    }
}