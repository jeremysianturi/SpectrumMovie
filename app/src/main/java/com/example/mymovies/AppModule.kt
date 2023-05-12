package com.example.mymovies

import com.example.core.domain.usecase.nowplaying.NowPlayingInteractor
import com.example.core.domain.usecase.nowplaying.NowPlayingUsecase
import com.example.core.domain.usecase.toprated.TopRatedInteractor
import com.example.core.domain.usecase.detailmovie.DetailMovieInteractor
import com.example.core.domain.usecase.detailmovie.DetailMovieUsecase
import com.example.core.domain.usecase.genre.GenreInteractor
import com.example.core.domain.usecase.genre.GenreUsecase
import com.example.core.domain.usecase.popularmovies.PopularMoviesInteractor
import com.example.core.domain.usecase.popularmovies.PopularMoviesUsecase
import com.example.core.domain.usecase.popularmoviesgrid.PopularMoviesGridInteractor
import com.example.core.domain.usecase.popularmoviesgrid.PopularMoviesGridUsecase
import com.example.core.domain.usecase.toprated.TopRatedUsecase
import com.example.core.domain.usecase.upcoming.UpcomingInteractor
import com.example.core.domain.usecase.upcoming.UpcomingUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideNowPlayingUsecase(nowPlayingInteractor: NowPlayingInteractor): NowPlayingUsecase

    @Binds
    abstract fun providePopularMoviesUsecase(popularMoviesInteractor: PopularMoviesInteractor): PopularMoviesUsecase

    @Binds
    abstract fun provideTopRatedUsecase(topRatedInteractor: TopRatedInteractor): TopRatedUsecase

    @Binds
    abstract fun provideUpcomingUsecase(upcomingInteractor: UpcomingInteractor): UpcomingUsecase

    @Binds
    abstract fun provideGenreUsecase(genreInteractor: GenreInteractor): GenreUsecase

    @Binds
    abstract fun provideDetailMovieUsecase(detailMovieInteractor: DetailMovieInteractor): DetailMovieUsecase

    @Binds
    abstract fun providePopularMoviesGridUsecase(popularMoviesGridInteractor: PopularMoviesGridInteractor): PopularMoviesGridUsecase
}