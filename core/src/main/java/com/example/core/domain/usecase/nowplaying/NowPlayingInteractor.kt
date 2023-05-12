package com.example.core.domain.usecase.nowplaying

import com.example.core.data.Resource
import com.example.core.data.repository.NowPlayingRepository
import com.example.core.domain.model.NowPlaying
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NowPlayingInteractor @Inject constructor(private val nowPlayingRepository: NowPlayingRepository) :
    NowPlayingUsecase {

    override fun getNowPlaying(apiKey: String, page: String): Flow<Resource<List<NowPlaying>>> =
        nowPlayingRepository.getNowPlaying(apiKey,page)

}