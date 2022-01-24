package com.nery.bustos.mymovies.data

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.mymovies.data.db.MovieEntity
import com.nery.bustos.mymovies.data.network.MovieResponse
import com.nery.bustos.mymovies.data.network.VideoResponse
import com.nery.bustos.mymovies.TypeMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchMovieRemote(typeMovie: TypeMovie) : Flow<DataState<MovieResponse>>
    suspend fun fetchMovieLocal(typeMovie: TypeMovie): Flow<List<MovieEntity>>
    suspend fun fetchVideo(id:Int) : Flow<DataState<VideoResponse>>
}