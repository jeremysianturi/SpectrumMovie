package com.example.core.domain.usecase.toprated

import com.example.core.data.Resource
import com.example.core.data.repository.TopRatedRepository
import com.example.core.domain.model.TopRated
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedInteractor @Inject constructor(private val topRatedRepository: TopRatedRepository) :
    TopRatedUsecase {

    override fun getTopRated(apiKey: String,page: String): Flow<Resource<List<TopRated>>> =
        topRatedRepository.getTopRated(apiKey,page)


}