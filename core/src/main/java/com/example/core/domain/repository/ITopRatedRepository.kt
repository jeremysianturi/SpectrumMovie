package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.TopRated
import kotlinx.coroutines.flow.Flow

interface ITopRatedRepository {

    fun getTopRated(apiKey: String,page: String) : Flow<Resource<List<TopRated>>>

}