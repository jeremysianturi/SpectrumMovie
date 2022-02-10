package com.example.core.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loved")
data class LovedEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "genres_name")
    val genresName: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String
    )