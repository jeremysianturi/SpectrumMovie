package com.example.mymovies

import com.example.core.domain.usecase.banner.BannerInteractor
import com.example.core.domain.usecase.banner.BannerUsecase
import com.example.core.domain.usecase.comingsoon.ComingSoonInteractor
import com.example.core.domain.usecase.comingsoon.ComingSoonUsecase
import com.example.core.domain.usecase.detailmovie.DetailMovieInteractor
import com.example.core.domain.usecase.detailmovie.DetailMovieUsecase
import com.example.core.domain.usecase.popularmovies.PopularMoviesInteractor
import com.example.core.domain.usecase.popularmovies.PopularMoviesUsecase
import com.example.core.domain.usecase.popularmoviesgrid.PopularMoviesGridInteractor
import com.example.core.domain.usecase.popularmoviesgrid.PopularMoviesGridUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideBannerUsecase(bannerInteractor: BannerInteractor): BannerUsecase

    @Binds
    abstract fun providePopularMoviesUsecase(popularMoviesInteractor: PopularMoviesInteractor): PopularMoviesUsecase

    @Binds
    abstract fun provideComingSoonUsecase(comingSoonInteractor: ComingSoonInteractor): ComingSoonUsecase

    @Binds
    abstract fun provideDetailMovieUsecase(detailMovieInteractor: DetailMovieInteractor): DetailMovieUsecase

    @Binds
    abstract fun providePopularMoviesGridUsecase(popularMoviesGridInteractor: PopularMoviesGridInteractor): PopularMoviesGridUsecase
}