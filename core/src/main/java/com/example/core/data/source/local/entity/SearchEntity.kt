package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "search")
data class SearchEntity(

    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "genre_ids")
    val genreIds: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "video")
    val video: Boolean?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,

    )