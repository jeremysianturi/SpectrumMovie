package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import kotlinx.coroutines.flow.Flow

interface IDetailMovieRepository {

    fun getDetailMovie(movieId: String, apiKey: String, language: String) : Flow<Resource<List<DetailMovie>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

//    fun getSearchBanner(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}