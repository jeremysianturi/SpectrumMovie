package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.banner.ListBannerResponse
import com.example.core.data.source.remote.response.comingsoon.ListComingSoonResponse
import com.example.core.data.source.remote.response.detailmovie.DetailMovieResponse
import com.example.core.data.source.remote.response.detailmovie.ListDetailMovieResponse
import com.example.core.data.source.remote.response.popularmovies.ListPopularMoviesResponse
import com.example.core.data.source.remote.response.popularmoviesgrid.ListPopularMovieGridResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // banner
    @GET("discover/movie")
    suspend fun getBanner(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: String
    ): ListBannerResponse

    // popular movies
    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: String
    ): ListPopularMoviesResponse


    // coming soon
    @GET("discover/movie")
    suspend fun getComingSoon(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: String,
        @Query("year") year: String
    ): ListComingSoonResponse

    // detail movie
    @GET("movie/{movieId}")
    suspend fun getDetailMovie (
        @Path(value = "movieId",encoded = true) movieId : String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): ListDetailMovieResponse


    // popular movies grid
    @GET("discover/movie")
    suspend fun getPopularMoviesGrid(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: String,
        @Query("year") year: String
    ): ListPopularMovieGridResponse
}