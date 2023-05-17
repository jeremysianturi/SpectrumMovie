package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.NowPlaying
import kotlinx.coroutines.flow.Flow

interface INowPlayingRepository {

    fun getNowPlaying(apiKey: String, page: String) : Flow<Resource<List<NowPlaying>>>

}