package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.banner.BannerResponse
import com.example.core.domain.model.Banner
import com.example.core.domain.repository.IBannerRepository
import com.example.core.helper.helpermapper.DataMapperBanner
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BannerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBannerRepository {

    override fun getBanner(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String): Flow<Resource<List<Banner>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Banner>, List<BannerResponse>>() {

            override fun loadFromDB(): Flow<List<Banner>> {
                return localDataSource.getBanner().map {
                    DataMapperBanner.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Banner>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<BannerResponse>>> =
                remoteDataSource.getBanner(apiKey, language, sortBy, includeAdult, includeVideo, page)

            override suspend fun saveCallResult(data: List<BannerResponse>) {
                val list = DataMapperBanner.mapResponsetoEntities(data)
                localDataSource.insertBanner(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteBanner()
            }

        }.asFlow()
}