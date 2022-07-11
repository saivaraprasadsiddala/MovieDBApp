package com.app.moviedbapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviedbapp.movies.upcoming.UpcomingRemoteKeys

@Dao
interface UpcomingRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<UpcomingRemoteKeys>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKey(remoteKey: UpcomingRemoteKeys)

    @Query("SELECT * FROM remote_keys")
    fun getKeys():List<UpcomingRemoteKeys>
}