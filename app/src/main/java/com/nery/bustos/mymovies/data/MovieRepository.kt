package com.nery.bustos.mymovies.data

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.mymovies.presentation.TypeMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchMovieRemote(type:TypeMovie) : Flow<DataState<MovieResponse>>
    suspend fun fetchMovieLocal()
    suspend fun fetchVideo(id:Int) : Flow<DataState<VideoResponse>>
}