package com.app.moviedbapp.movies.upcoming

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_remote_keys")
data class UpcomingRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextKey: Int?,
    val isEndReached: Boolean
)