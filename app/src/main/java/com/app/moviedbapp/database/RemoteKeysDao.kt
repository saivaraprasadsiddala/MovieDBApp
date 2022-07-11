package com.app.moviedbapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviedbapp.movies.popular.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<RemoteKeys>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKey(remoteKey: RemoteKeys)

    @Query("SELECT * FROM remote_keys")
    fun getKeys():List<RemoteKeys>
}