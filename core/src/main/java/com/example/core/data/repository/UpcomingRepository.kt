package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.upcoming.UpcomingResponse
import com.example.core.domain.model.Upcoming
import com.example.core.domain.repository.IUpcomingRepository
import com.example.core.helper.helpermapper.DataMapperUpcoming
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpcomingRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUpcomingRepository {

    override fun getUpcoming(apiKey: String, page: String): Flow<Resource<List<Upcoming>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Upcoming>, List<UpcomingResponse>>() {

            override fun loadFromDB(): Flow<List<Upcoming>> {
                return localDataSource.getUpcoming().map {
                    DataMapperUpcoming.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Upcoming>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<UpcomingResponse>>> =
                remoteDataSource.getUpcoming(apiKey, page)

            override suspend fun saveCallResult(data: List<UpcomingResponse>) {
                val list = DataMapperUpcoming.mapResponsetoEntities(data)
                localDataSource.insertUpcoming(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteUpcoming()
            }

        }.asFlow()
}