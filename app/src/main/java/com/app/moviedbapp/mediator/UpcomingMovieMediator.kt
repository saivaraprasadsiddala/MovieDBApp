package com.app.moviedbapp.mediator


import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.app.moviedbapp.BuildConfig
import com.app.moviedbapp.database.MovieDataBase
import com.app.moviedbapp.movies.upcoming.UpcomingMovieItem
import com.app.moviedbapp.movies.upcoming.UpcomingRemoteKeys
import com.app.moviedbapp.network.MoviesApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * This class is used to download movie data from server and store it in local database.
 *
 */
@OptIn(ExperimentalPagingApi::class)
class UpcomingMovieMediator(private val apiService: MoviesApiService,
                        private val movieDb: MovieDataBase
): RemoteMediator<Int, UpcomingMovieItem>() {
    var errorHandleLiveData = MutableLiveData<String>()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UpcomingMovieItem>
    ): MediatorResult {
        val key  = when(loadType) {
            LoadType.APPEND -> {
                CoroutineScope(Dispatchers.IO).async {
                    getKey()
                }.await()
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.REFRESH -> {
                CoroutineScope(Dispatchers.IO).launch {
                    if (movieDb.upcomingMovieItemDao().count() > 0)
                        MediatorResult.Success(false)
                }
                null
            }
        }
        return try {
            if (key != null) {
                if (key.isEndReached) return MediatorResult.Success(endOfPaginationReached = true)
            }

            val page: Int = key?.nextKey ?: 1
            val response = apiService.getUpcomingMovies(BuildConfig.API_KEY, page)
            val isEndOfPageReached = response.results?.isEmpty() == true
            CoroutineScope(Dispatchers.IO).launch {
                movieDb.withTransaction {
                    //Storing page number loaded into database.
                    val nextKey = page + 1
                    movieDb.upcomingKeyDao().insertKey(
                        UpcomingRemoteKeys(
                            0,
                            nextKey = nextKey,
                            isEndReached = isEndOfPageReached
                        )
                    )
                }
                response.results?.let { movies ->
                    //Inserting movie data to database
                    movieDb.upcomingMovieItemDao().insertAll(movies)
                }
            }
            if (isEndOfPageReached) {
                errorHandleLiveData.postValue("No further data is available.")
            }
            MediatorResult.Success(isEndOfPageReached)
        } catch (exp : Exception) {
            errorHandleLiveData.postValue(exp.message)
            MediatorResult.Error(exp)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    private suspend fun getKey(): UpcomingRemoteKeys? {
        return movieDb.upcomingKeyDao().getKeys().firstOrNull()
    }
}