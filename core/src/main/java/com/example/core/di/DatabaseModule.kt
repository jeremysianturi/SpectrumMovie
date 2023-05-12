package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.data.source.local.room.dao.*
import com.example.core.utils.LovedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "Movie.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideNowPlayingDao(database: MovieDatabase): NowPlayingDao = database.nowPlayingDao()

    @Provides
    fun providePopularMoviesDao(database: MovieDatabase): PopularMoviesDao = database.popularMoviesDao()

    @Provides
    fun provideTopRatedDao(database: MovieDatabase): TopRatedDao = database.topRatedDao()

    @Provides
    fun provideUpcomingDao(database: MovieDatabase): UpcomingDao = database.upcomingDao()

    @Provides
    fun provideGenreDao(database: MovieDatabase): GenreDao = database.genreDao()

    @Provides
    fun provideDetailMovieDao(database: MovieDatabase): DetailMovieDao = database.detailMovieDao()

    @Provides
    fun providePopularMoviesGridDao(database: MovieDatabase): PopularMoviesGridDao = database.popularMoviesGridDao()

    @Provides
    fun provideLovedDao(database: MovieDatabase): LovedDao = database.lovedDao()

}