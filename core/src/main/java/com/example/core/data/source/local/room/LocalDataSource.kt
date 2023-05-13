package com.example.core.data.source.local.room

import com.example.core.data.source.local.entity.*
import com.example.core.data.source.local.room.dao.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val mNowPlayingDao: NowPlayingDao,
    private val mPopularMoviesDao: PopularMoviesDao,
    private val mTopRatedDao: TopRatedDao,
    private val mUpcomingDao: UpcomingDao,
    private val mGenreDao: GenreDao,
    private val mDetailMovieDao: DetailMovieDao,
    private val mSearchDao: SearchDao,
    private val mPopularMoviesGridDao: PopularMoviesGridDao,
) {

    // NowPlaying
    fun getNowPlaying(): Flow<List<NowPlayingEntity>> = mNowPlayingDao.getNowPlaying()
    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>) = mNowPlayingDao.insertAndDeleteNowPlaying(nowPlaying)
    suspend fun deleteNowPlaying() = mNowPlayingDao.deleteNowPlaying()


    // Popular Movies
    fun getPopularMovies(): Flow<List<PopularMoviesEntity>> = mPopularMoviesDao.getPopularMovies()
    suspend fun insertPopularMovies(popularMovies: List<PopularMoviesEntity>) = mPopularMoviesDao.insertAndDeletePopularMovies(popularMovies)
    suspend fun deletePopularMovies() = mPopularMoviesDao.deletePopularMovies()


    // TopRated
    fun getTopRated(): Flow<List<TopRatedEntity>> = mTopRatedDao.getTopRated()
    suspend fun insertTopRated(topRated: List<TopRatedEntity>) = mTopRatedDao.insertAndDeleteTopRated(topRated)
    suspend fun deleteTopRated() = mTopRatedDao.deleteTopRated()

    // Upcoming
    fun getUpcoming(): Flow<List<UpcomingEntity>> = mUpcomingDao.getUpcoming()
    suspend fun insertUpcoming(upcoming: List<UpcomingEntity>) = mUpcomingDao.insertAndDeleteUpcoming(upcoming)
    suspend fun deleteUpcoming() = mUpcomingDao.deleteUpcoming()

    // Genre
    fun getGenre(): Flow<List<GenreEntity>> = mGenreDao.getGenre()
    suspend fun insertGenre(genre: List<GenreEntity>) = mGenreDao.insertAndDeleteGenre(genre)
    suspend fun deleteGenre() = mGenreDao.deleteGenre()

    // Detail Movie
    fun getDetailMovie(): Flow<List<DetailMovieEntity>> = mDetailMovieDao.getDetailMovie()
    suspend fun insertDetailMovie(detailMovie: List<DetailMovieEntity>) = mDetailMovieDao.insertAndDeleteDetailMovie(detailMovie)
    suspend fun deleteDetailMovie() = mDetailMovieDao.deleteDetailMovie()

    // Search
    fun getSearch(): Flow<List<SearchEntity>> = mSearchDao.getSearch()
    suspend fun insertSearch(search: List<SearchEntity>) = mSearchDao.insertAndDeleteSearch(search)
    suspend fun deleteSearch() = mSearchDao.deleteSearch()

    // Popular Movies Grid
    fun getPopularMoviesGrid(): Flow<List<PopularMoviesGridEntity>> = mPopularMoviesGridDao.getPopularMoviesGrid()
    suspend fun insertPopularMoviesGrid(popularMoviesGrid: List<PopularMoviesGridEntity>) = mPopularMoviesGridDao.insertAndDeletePopularMoviesGrid(popularMoviesGrid)
    suspend fun deletePopularMoviesGrid() = mPopularMoviesGridDao.deletePopularMoviesGrid()
    fun getSearchPopularMoviesGrid(search: String): Flow<List<PopularMoviesGridEntity>> =
        mPopularMoviesGridDao.getSearchPopularMoviesGrid(search)

}