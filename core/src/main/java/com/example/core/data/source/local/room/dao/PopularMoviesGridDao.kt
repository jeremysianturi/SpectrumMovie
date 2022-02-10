package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.PopularMoviesGridEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMoviesGridDao {

    @Query("SELECT * FROM popular_movies_grid")
    fun getPopularMoviesGrid(): Flow<List<PopularMoviesGridEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMoviesGrid(popularMoviesGrid: List<PopularMoviesGridEntity>)

    @Query("DELETE FROM popular_movies_grid")
    suspend fun deletePopularMoviesGrid()

    @Transaction
    suspend fun insertAndDeletePopularMoviesGrid(popularMoviesGrid: List<PopularMoviesGridEntity>) {
        deletePopularMoviesGrid()
        insertPopularMoviesGrid(popularMoviesGrid)
    }

    @Transaction
    @Query("SELECT * FROM popular_movies_grid WHERE original_title LIKE '%'|| :search || '%'")
    fun getSearchPopularMoviesGrid(search: String): Flow<List<PopularMoviesGridEntity>>

}