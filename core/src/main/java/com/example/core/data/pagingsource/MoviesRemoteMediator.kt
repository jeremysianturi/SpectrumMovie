package com.example.core.data.pagingsource

//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.example.core.data.source.local.entity.PopularMoviesGridEntity
//import com.example.core.data.source.local.entity.RemoteKeysEntity
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.data.source.remote.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

//@OptIn(ExperimentalPagingApi::class)
//class MoviesRemoteMediator (
//    private val moviesApiService: ApiService,
//    private val moviesDatabase: MovieDatabase,
//): RemoteMediator<Int, PopularMoviesGridEntity>() {
//
//    override suspend fun initialize(): InitializeAction {
//        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
//
//        return if (System.currentTimeMillis() - (moviesDatabase.remoteKeysDao().getCreationTime() ?: 0) < cacheTimeout) {
//            InitializeAction.SKIP_INITIAL_REFRESH
//        } else {
//            InitializeAction.LAUNCH_INITIAL_REFRESH
//        }
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, PopularMoviesGridEntity>
//    ): MediatorResult {
//        val page: Int = when (loadType) {
//            LoadType.REFRESH -> {
//                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: 1
//            }
//            LoadType.PREPEND -> {
//                val remoteKeys = getRemoteKeyForFirstItem(state)
//                val prevKey = remoteKeys?.prevKey
//                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//            }
//            LoadType.APPEND -> {
//                val remoteKeys = getRemoteKeyForLastItem(state)
//                val nextKey = remoteKeys?.nextKey
//                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//            }
//        }
//
//        try {
//            val apiResponse = moviesApiService.getPopularMovies(apiKey = "0e7274f05c36db12cbe71d9ab0393d47", page = page)
//
//            val movies = apiResponse.movies
//            val endOfPaginationReached = movies.isEmpty()
//
//            moviesDatabase.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    moviesDatabase.remoteKeysDao().clearRemoteKeys()
//                    moviesDatabase.popularMoviesGridDao().clearAllPopularMoviesGrid()
//                }
//                val prevKey = if (page > 1) page - 1 else null
//                val nextKey = if (endOfPaginationReached) null else page + 1
//                val remoteKeys = movies.map {
//                    RemoteKeysEntity(movieID = it.id, prevKey = prevKey, currentPage = page, nextKey = nextKey)
//                }
//
//                moviesDatabase.remoteKeysDao().insertAll(remoteKeys)
//                moviesDatabase.popularMoviesGridDao().insertAll(movies.onEachIndexed { _, movie -> movie.page = page })
//            }
//            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (error: IOException) {
//            return MediatorResult.Error(error)
//        } catch (error: HttpException) {
//            return MediatorResult.Error(error)
//        }
//    }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PopularMoviesGridEntity>): RemoteKeysEntity? {
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { id ->
//                moviesDatabase.remoteKeysDao().getRemoteKeyByMovieID(id)
//            }
//        }
//    }
//
//    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PopularMoviesGridEntity>): RemoteKeysEntity? {
//        return state.pages.firstOrNull {
//            it.data.isNotEmpty()
//        }?.data?.firstOrNull()?.let { movie ->
//            moviesDatabase.remoteKeysDao().getRemoteKeyByMovieID(movie.id)
//        }
//    }
//
//    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PopularMoviesGridEntity>): RemoteKeysEntity? {
//        return state.pages.lastOrNull {
//            it.data.isNotEmpty()
//        }?.data?.lastOrNull()?.let { movie ->
//            moviesDatabase.remoteKeysDao().getRemoteKeyByMovieID(movie.id)
//        }
//    }
//}