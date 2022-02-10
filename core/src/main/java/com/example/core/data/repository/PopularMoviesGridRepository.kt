package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.popularmoviesgrid.PopularMovieGridResponse
import com.example.core.domain.model.PopularMoviesGrid
import com.example.core.domain.repository.IPopularMoviesGridRepository
import com.example.core.helper.helpermapper.DataMapperPopularMoviesGrid
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesGridRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPopularMoviesGridRepository {

    override fun getPopularMoviesGrid(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, year: String): Flow<Resource<List<PopularMoviesGrid>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<PopularMoviesGrid>, List<PopularMovieGridResponse>>() {

            override fun loadFromDB(): Flow<List<PopularMoviesGrid>> {
                return localDataSource.getPopularMoviesGrid().map {
                    DataMapperPopularMoviesGrid.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<PopularMoviesGrid>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<PopularMovieGridResponse>>> =
                remoteDataSource.getPopularMoviesGrid(apiKey, language, sortBy, includeAdult, includeVideo, page, year)

            override suspend fun saveCallResult(data: List<PopularMovieGridResponse>) {
                val list = DataMapperPopularMoviesGrid.mapResponsetoEntities(data)
                localDataSource.insertPopularMoviesGrid(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deletePopularMoviesGrid()
            }

        }.asFlow()

    override fun getSearchPopularMoviesGrid(search: String): Flow<List<PopularMoviesGrid>> {
        return localDataSource.getSearchPopularMoviesGrid(search).map {
            DataMapperPopularMoviesGrid.mapEntitiestoDomain(it)
        }
    }
}