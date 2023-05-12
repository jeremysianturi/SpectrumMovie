package com.example.core.domain.usecase.toprated

import com.example.core.data.Resource
import com.example.core.domain.model.TopRated
import kotlinx.coroutines.flow.Flow

interface TopRatedUsecase {

    fun getTopRated(apiKey: String,page: String): Flow<Resource<List<TopRated>>>

}