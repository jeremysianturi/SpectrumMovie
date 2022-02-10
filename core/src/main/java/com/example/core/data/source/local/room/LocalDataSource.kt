package com.example.core.data.source.local.room

import com.example.core.data.source.local.entity.*
import com.example.core.data.source.local.room.dao.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val mBannerDao: BannerDao,
    private val mPopularMoviesDao: PopularMoviesDao,
    private val mComingSoonDao: ComingSoonDao,
    private val mDetailMovieDao: DetailMovieDao,
    private val mPopularMoviesGridDao: PopularMoviesGridDao,
) {

    // Banner
    fun getBanner(): Flow<List<BannerEntity>> = mBannerDao.getBanner()

    suspend fun insertBanner(banner: List<BannerEntity>) = mBannerDao.insertAndDeleteBanner(banner)

    suspend fun deleteBanner() = mBannerDao.deleteBanner()


    // Popular Movies
    fun getPopularMovies(): Flow<List<PopularMoviesEntity>> = mPopularMoviesDao.getPopularMovies()

    suspend fun insertPopularMovies(popularMovies: List<PopularMoviesEntity>) = mPopularMoviesDao.insertAndDeletePopularMovies(popularMovies)

    suspend fun deletePopularMovies() = mPopularMoviesDao.deletePopularMovies()


    // Coming Soon
    fun getComingSoon(): Flow<List<ComingSoonEntity>> = mComingSoonDao.getComingSoon()

    suspend fun insertComingSoon(comingSoon: List<ComingSoonEntity>) = mComingSoonDao.insertAndDeleteComingSoon(comingSoon)

    suspend fun deleteComingSoon() = mComingSoonDao.deleteComingSoon()


    // Detail Movie
    fun getDetailMovie(): Flow<List<DetailMovieEntity>> = mDetailMovieDao.getDetailMovie()

    suspend fun insertDetailMovie(detailMovie: List<DetailMovieEntity>) = mDetailMovieDao.insertAndDeleteDetailMovie(detailMovie)

    suspend fun deleteDetailMovie() = mDetailMovieDao.deleteDetailMovie()


    // Popular Movies Grid
    fun getPopularMoviesGrid(): Flow<List<PopularMoviesGridEntity>> = mPopularMoviesGridDao.getPopularMoviesGrid()

    suspend fun insertPopularMoviesGrid(popularMoviesGrid: List<PopularMoviesGridEntity>) = mPopularMoviesGridDao.insertAndDeletePopularMoviesGrid(popularMoviesGrid)

    suspend fun deletePopularMoviesGrid() = mPopularMoviesGridDao.deletePopularMoviesGrid()

    fun getSearchPopularMoviesGrid(search: String): Flow<List<PopularMoviesGridEntity>> =
        mPopularMoviesGridDao.getSearchPopularMoviesGrid(search)

}