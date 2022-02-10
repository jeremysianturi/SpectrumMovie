package com.example.core.domain.usecase.banner

import com.example.core.data.Resource
import com.example.core.data.repository.BannerRepository
import com.example.core.domain.model.Banner
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BannerInteractor @Inject constructor(private val bannerRepository: BannerRepository) :
    BannerUsecase {

    override fun getBanner(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String): Flow<Resource<List<Banner>>> =
        bannerRepository.getBanner(apiKey, language, sortBy, includeAdult, includeVideo, page)

}