package com.example.core.domain.usecase.comingsoon

import com.example.core.data.Resource
import com.example.core.domain.model.Banner
import com.example.core.domain.model.ComingSoon
import kotlinx.coroutines.flow.Flow

interface ComingSoonUsecase {

    fun getComingSoon(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, year: String): Flow<Resource<List<ComingSoon>>>

}