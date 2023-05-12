package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.UpcomingEntity
import com.example.core.data.source.remote.response.genre.GenreResponse
import com.example.core.data.source.remote.response.upcoming.UpcomingResponse
import com.example.core.domain.model.Genre
import com.example.core.domain.model.Upcoming

object DataMapperGenre {

    fun mapResponsetoEntities(input: List<GenreResponse>): List<GenreEntity> {
        val genreList = ArrayList<GenreEntity>()
        input.map {
            val genre = GenreEntity(
                id = it.id,
                name = it.name
            )
            genreList.add(genre)
        }
        return genreList
    }

    fun mapEntitiestoDomain(input: List<GenreEntity>): List<Genre> =
        input.map {
            Genre(
                id = it.id,
                name = it.name
            )
        }
}