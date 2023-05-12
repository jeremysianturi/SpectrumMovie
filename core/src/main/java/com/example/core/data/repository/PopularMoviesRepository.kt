package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.popularmovies.PopularMovieResponse
import com.example.core.domain.model.PopularMovies
import com.example.core.domain.repository.IPopularMoviesRepository
import com.example.core.helper.helpermapper.DataMapperPopularMovies
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPopularMoviesRepository {

    override fun getPopularMovies(apiKey: String,page: String): Flow<Resource<List<PopularMovies>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<PopularMovies>, List<PopularMovieResponse>>() {

            override fun loadFromDB(): Flow<List<PopularMovies>> {
                return localDataSource.getPopularMovies().map {
                    DataMapperPopularMovies.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<PopularMovies>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<PopularMovieResponse>>> =
                remoteDataSource.getPopularMovies(apiKey,page)

            override suspend fun saveCallResult(data: List<PopularMovieResponse>) {
                val list = DataMapperPopularMovies.mapResponsetoEntities(data)
                localDataSource.insertPopularMovies(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deletePopularMovies()
            }

        }.asFlow()
}