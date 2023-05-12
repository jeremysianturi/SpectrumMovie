package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.genre.GenreResponse
import com.example.core.domain.model.Genre
import com.example.core.domain.repository.IGenreRepository
import com.example.core.helper.helpermapper.DataMapperGenre
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGenreRepository {

    override fun getGenre(apiKey: String): Flow<Resource<List<Genre>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Genre>, List<GenreResponse>>() {

            override fun loadFromDB(): Flow<List<Genre>> {
                return localDataSource.getGenre().map {
                    DataMapperGenre.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Genre>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<GenreResponse>>> =
                remoteDataSource.getGenre(apiKey)

            override suspend fun saveCallResult(data: List<GenreResponse>) {
                val list = DataMapperGenre.mapResponsetoEntities(data)
                localDataSource.insertGenre(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteGenre()
            }

        }.asFlow()
}