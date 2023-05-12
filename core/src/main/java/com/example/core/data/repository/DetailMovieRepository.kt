package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.detailmovie.DetailMovieResponse
import com.example.core.domain.model.DetailMovie
import com.example.core.domain.repository.IDetailMovieRepository
import com.example.core.helper.helpermapper.DataMapperDetailMovie
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailMovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDetailMovieRepository {

    override fun getDetailMovie(movieId: String,apiKey: String): Flow<Resource<List<DetailMovie>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<DetailMovie>, DetailMovieResponse>() {

            override fun loadFromDB(): Flow<List<DetailMovie>> {
                return localDataSource.getDetailMovie().map {
                    DataMapperDetailMovie.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<DetailMovie>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(movieId, apiKey)

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                val list = DataMapperDetailMovie.mapResponsetoEntities(listOf(data))
                localDataSource.insertDetailMovie(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteDetailMovie()
            }

        }.asFlow()
}