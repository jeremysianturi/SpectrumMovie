package com.example.core.utils

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LovedDao {
    @Query("SELECT * FROM loved")
    fun getAllLoved(): List<LovedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg books: LovedEntity)

    @Delete
    fun delete(book: LovedEntity)

    @Transaction
    @Query("SELECT * FROM loved where title LIKE '%'|| :search || '%'")
    fun getSearchLoved(search: String): Flow<List<LovedEntity>>
}