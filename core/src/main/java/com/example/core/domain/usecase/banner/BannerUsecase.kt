package com.example.core.domain.usecase.banner

import com.example.core.data.Resource
import com.example.core.domain.model.Banner
import kotlinx.coroutines.flow.Flow

interface BannerUsecase {

    fun getBanner(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String): Flow<Resource<List<Banner>>>

}