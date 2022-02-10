package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Banner
import kotlinx.coroutines.flow.Flow

interface IBannerRepository {

    fun getBanner(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) : Flow<Resource<List<Banner>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

//    fun getSearchBanner(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}