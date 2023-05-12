package com.example.core.domain.usecase.nowplaying

import com.example.core.data.Resource
import com.example.core.domain.model.NowPlaying
import kotlinx.coroutines.flow.Flow

interface NowPlayingUsecase {

    fun getNowPlaying(apiKey: String, page: String): Flow<Resource<List<NowPlaying>>>

}