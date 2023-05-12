package com.example.core.domain.usecase.upcoming

import com.example.core.data.Resource
import com.example.core.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow

interface UpcomingUsecase {

    fun getUpcoming(apiKey: String, page: String): Flow<Resource<List<Upcoming>>>

}