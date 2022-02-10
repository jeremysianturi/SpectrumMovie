package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow

interface IPopularMoviesGridRepository {

    fun getPopularMoviesGrid(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, year: String) : Flow<Resource<List<PopularMoviesGrid>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

    fun getSearchPopularMoviesGrid(search: String): Flow<List<PopularMoviesGrid>>
}