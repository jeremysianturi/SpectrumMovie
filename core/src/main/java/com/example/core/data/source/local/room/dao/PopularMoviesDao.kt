package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.PopularMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMoviesDao {

    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): Flow<List<PopularMoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMovies: List<PopularMoviesEntity>)

    @Query("DELETE FROM popular_movies")
    suspend fun deletePopularMovies()

    @Transaction
    suspend fun insertAndDeletePopularMovies(popularMovies: List<PopularMoviesEntity>) {
        deletePopularMovies()
        insertPopularMovies(popularMovies)
    }

}