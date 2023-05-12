package com.example.core.domain.usecase.upcoming

import com.example.core.data.Resource
import com.example.core.data.repository.UpcomingRepository
import com.example.core.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingInteractor @Inject constructor(private val upcomingRepository: UpcomingRepository) :
    UpcomingUsecase {

    override fun getUpcoming(apiKey: String, page: String): Flow<Resource<List<Upcoming>>> =
        upcomingRepository.getUpcoming(apiKey,page)

}