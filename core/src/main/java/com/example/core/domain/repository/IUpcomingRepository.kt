package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow

interface IUpcomingRepository {

    fun getUpcoming(apiKey: String, page: String) : Flow<Resource<List<Upcoming>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

//    fun getSearchBanner(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}