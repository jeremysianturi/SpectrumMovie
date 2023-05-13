package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {

    fun getSearch(apiKey: String, query: String, page: String) : Flow<Resource<List<Search>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

//    fun getSearchBanner(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}