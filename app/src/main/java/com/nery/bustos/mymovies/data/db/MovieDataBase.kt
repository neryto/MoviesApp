package com.nery.bustos.mymovies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}