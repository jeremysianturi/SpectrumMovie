package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.*
import com.example.core.data.source.local.room.dao.*
import com.example.core.utils.LovedDao
import com.example.core.utils.LovedEntity

@Database(
    entities = [
        BannerEntity::class,
        PopularMoviesEntity::class,
        ComingSoonEntity::class,
        DetailMovieEntity::class,
        PopularMoviesGridEntity::class,
        LovedEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class MovieDatabase : RoomDatabase() {

    abstract fun bannerDao(): BannerDao

    abstract fun popularMoviesDao(): PopularMoviesDao

    abstract fun comingSoonDao(): ComingSoonDao

    abstract fun detailMovieDao(): DetailMovieDao

    abstract fun popularMoviesGridDao(): PopularMoviesGridDao

    abstract fun lovedDao(): LovedDao


}