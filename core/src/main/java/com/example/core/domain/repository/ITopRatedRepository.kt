package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.TopRated
import kotlinx.coroutines.flow.Flow

interface ITopRatedRepository {

    fun getTopRated(apiKey: String,page: String) : Flow<Resource<List<TopRated>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

//    fun getSearchBanner(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}