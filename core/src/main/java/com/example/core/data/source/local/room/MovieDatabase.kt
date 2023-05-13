package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.*
import com.example.core.data.source.local.room.dao.*
import com.example.core.utils.LovedDao
import com.example.core.utils.LovedEntity

@Database(
    entities = [
        NowPlayingEntity::class,
        PopularMoviesEntity::class,
        TopRatedEntity::class,
        UpcomingEntity::class,
        GenreEntity::class,
        DetailMovieEntity::class,
        SearchEntity::class,
        PopularMoviesGridEntity::class,
        LovedEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class MovieDatabase : RoomDatabase() {

    abstract fun nowPlayingDao(): NowPlayingDao

    abstract fun popularMoviesDao(): PopularMoviesDao

    abstract fun topRatedDao(): TopRatedDao

    abstract fun upcomingDao(): UpcomingDao

    abstract fun genreDao(): GenreDao

    abstract fun detailMovieDao(): DetailMovieDao

    abstract fun searchDao(): SearchDao

    abstract fun popularMoviesGridDao(): PopularMoviesGridDao

    abstract fun lovedDao(): LovedDao


}