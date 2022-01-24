package com.nery.bustos.mymovies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity WHERE type = 1")
    fun getMostPopular(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity WHERE type = 0")
    fun getPlayingNow(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertMovie(movies: List<MovieEntity>)
}