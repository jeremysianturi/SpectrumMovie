package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.search.SearchResponse
import com.example.core.domain.model.Search
import com.example.core.domain.repository.ISearchRepository
import com.example.core.helper.helpermapper.DataMapperSearch
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISearchRepository {

    override fun getSearch(apiKey: String, query: String, page: String): Flow<Resource<List<Search>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Search>, List<SearchResponse>>() {

            override fun loadFromDB(): Flow<List<Search>> {
                return localDataSource.getSearch().map {
                    DataMapperSearch.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Search>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<SearchResponse>>> =
                remoteDataSource.getSearch(apiKey,query,page)

            override suspend fun saveCallResult(data: List<SearchResponse>) {
                val list = DataMapperSearch.mapResponsetoEntities(data)
                localDataSource.insertSearch(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteSearch()
            }

        }.asFlow()
}