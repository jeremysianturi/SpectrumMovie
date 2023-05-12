package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.nowplaying.NowPlayingResponse
import com.example.core.domain.model.NowPlaying
import com.example.core.domain.repository.INowPlayingRepository
import com.example.core.helper.helpermapper.DataMapperNowPlaying
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NowPlayingRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INowPlayingRepository {

    override fun getNowPlaying(apiKey: String, page: String): Flow<Resource<List<NowPlaying>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<NowPlaying>, List<NowPlayingResponse>>() {

            override fun loadFromDB(): Flow<List<NowPlaying>> {
                return localDataSource.getNowPlaying().map {
                    DataMapperNowPlaying.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<NowPlaying>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<NowPlayingResponse>>> =
                remoteDataSource.getNowPlaying(apiKey, page)

            override suspend fun saveCallResult(data: List<NowPlayingResponse>) {
                val list = DataMapperNowPlaying.mapResponsetoEntities(data)
                localDataSource.insertNowPlaying(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteNowPlaying()
            }

        }.asFlow()
}