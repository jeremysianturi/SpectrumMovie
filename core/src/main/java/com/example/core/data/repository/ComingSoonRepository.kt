package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.comingsoon.ComingSoonResponse
import com.example.core.domain.model.ComingSoon
import com.example.core.domain.repository.IComingSoonRepository
import com.example.core.helper.helpermapper.DataMapperComingSoon
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComingSoonRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IComingSoonRepository {

    override fun getComingSoon(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, year: String): Flow<Resource<List<ComingSoon>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<ComingSoon>, List<ComingSoonResponse>>() {

            override fun loadFromDB(): Flow<List<ComingSoon>> {
                return localDataSource.getComingSoon().map {
                    DataMapperComingSoon.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<ComingSoon>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ComingSoonResponse>>> =
                remoteDataSource.getComingSoon(apiKey, language, sortBy, includeAdult, includeVideo, page, year)

            override suspend fun saveCallResult(data: List<ComingSoonResponse>) {
                val list = DataMapperComingSoon.mapResponsetoEntities(data)
                localDataSource.insertComingSoon(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteComingSoon()
            }

        }.asFlow()
}