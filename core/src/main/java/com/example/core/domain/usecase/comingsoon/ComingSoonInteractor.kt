package com.example.core.domain.usecase.comingsoon

import com.example.core.data.Resource
import com.example.core.data.repository.ComingSoonRepository
import com.example.core.domain.model.ComingSoon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComingSoonInteractor @Inject constructor(private val comingSoonRepository: ComingSoonRepository) :
    ComingSoonUsecase {

    override fun getComingSoon(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, year: String): Flow<Resource<List<ComingSoon>>> =
        comingSoonRepository.getComingSoon(apiKey, language, sortBy, includeAdult, includeVideo, page, year)


}