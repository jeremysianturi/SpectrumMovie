package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface IGenreRepository {

    fun getGenre(apiKey: String) : Flow<Resource<List<Genre>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

//    fun getSearchBanner(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}