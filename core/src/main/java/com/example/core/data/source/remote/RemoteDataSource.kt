package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.nowplaying.NowPlayingResponse
import com.example.core.data.source.remote.response.toprated.TopRatedResponse
import com.example.core.data.source.remote.response.detailmovie.DetailMovieResponse
import com.example.core.data.source.remote.response.genre.GenreResponse
import com.example.core.data.source.remote.response.popularmovies.PopularMovieResponse
import com.example.core.data.source.remote.response.popularmoviesgrid.PopularMovieGridResponse
import com.example.core.data.source.remote.response.upcoming.UpcomingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {

    private val tag = RemoteDataSource::class.java.simpleName.toString()

    suspend fun getNowPlaying(
        apiKey: String,
        page: String
    ): Flow<ApiResponse<List<NowPlayingResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlaying(apiKey, page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMovies(
        apiKey: String,
        page: String
    ): Flow<ApiResponse<List<PopularMovieResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMovies(apiKey,page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRated(
        apiKey: String,
        page: String
    ): Flow<ApiResponse<List<TopRatedResponse>>> {
        return flow {
            try {
                val response = apiService.getTopRated(apiKey,page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUpcoming(
        apiKey: String,
        page: String
    ): Flow<ApiResponse<List<UpcomingResponse>>> {
        return flow {
            try {
                val response = apiService.getUpcoming(apiKey,page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGenre(
        apiKey: String
    ): Flow<ApiResponse<List<GenreResponse>>> {
        return flow {
            try {
                val response = apiService.getGenre(apiKey)
                val dataArray = response.genres
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.genres))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(
        movieId: String,
        apiKey: String
    ): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId,apiKey)
                val dataArray = response
                Timber.d("check data array di remote data source: $dataArray")
                if (dataArray.toString() != "null") {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMoviesGrid(
        apiKey: String,
        page: String
    ): Flow<ApiResponse<List<PopularMovieGridResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMoviesGrid(apiKey,page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}