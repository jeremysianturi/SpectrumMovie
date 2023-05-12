package com.example.core.data.source.remote.response.genre

import com.example.core.data.source.remote.response.upcoming.UpcomingResponse
import com.google.gson.annotations.SerializedName

data class ListGenreResponse (
    @field:SerializedName("genres")
    val genres: List<GenreResponse>,
)