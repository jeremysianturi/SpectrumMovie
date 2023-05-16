package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.post.LovedPost
import com.example.core.data.source.remote.response.SubmitResponse
import com.example.core.data.source.remote.response.detailmovie.DetailMovieResponse
import com.example.core.data.source.remote.response.nowplaying.ListNowPlayingResponse
import com.example.core.data.source.remote.response.toprated.ListTopRatedResponse
import com.example.core.data.source.remote.response.detailmovie.ListDetailMovieResponse
import com.example.core.data.source.remote.response.genre.ListGenreResponse
import com.example.core.data.source.remote.response.popularmovies.ListPopularMoviesResponse
import com.example.core.data.source.remote.response.popularmoviesgrid.ListPopularMovieGridResponse
import com.example.core.data.source.remote.response.search.ListSearchResponse
import com.example.core.data.source.remote.response.upcoming.ListUpcomingResponse
import retrofit2.http.*

interface ApiService {

    // now playing
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("page") page: String
    ): ListNowPlayingResponse

    // popular
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String
    ): ListPopularMoviesResponse

    // top rated
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("page") page: String
    ): ListTopRatedResponse

    // upcoming
    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("page") page: String
    ): ListUpcomingResponse

    // genre
    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String
    ): ListGenreResponse


    // detail movie
    @GET("movie/{movieId}")
    suspend fun getDetailMovie (
        @Path(value = "movieId",encoded = true) movieId : String,
        @Query("api_key") apiKey: String,
    ): DetailMovieResponse

    // search
    @GET("search/movie")
    suspend fun getSearch (
        @Query("api_key") apiKey: String,
        @Query(value = "query",encoded = true) query : String,
        @Query("page") page: String
    ): ListSearchResponse


    // popular movies grid
    @GET("discover/movie")
    suspend fun getPopularMoviesGrid(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
    ): ListPopularMovieGridResponse

    @POST("/api/loved")
    suspend fun postLoved(
        @Body lovedPost: LovedPost
    ): SubmitResponse
}