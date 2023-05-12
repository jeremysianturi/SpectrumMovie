package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.toprated.TopRatedResponse
import com.example.core.domain.model.TopRated
import com.example.core.domain.repository.ITopRatedRepository
import com.example.core.helper.helpermapper.DataMapperTopRated
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITopRatedRepository {

    override fun getTopRated(apiKey: String,page: String): Flow<Resource<List<TopRated>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<TopRated>, List<TopRatedResponse>>() {

            override fun loadFromDB(): Flow<List<TopRated>> {
                return localDataSource.getTopRated().map {
                    DataMapperTopRated.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<TopRated>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<TopRatedResponse>>> =
                remoteDataSource.getTopRated(apiKey,page)

            override suspend fun saveCallResult(data: List<TopRatedResponse>) {
                val list = DataMapperTopRated.mapResponsetoEntities(data)
                localDataSource.insertTopRated(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteTopRated()
            }

        }.asFlow()
}