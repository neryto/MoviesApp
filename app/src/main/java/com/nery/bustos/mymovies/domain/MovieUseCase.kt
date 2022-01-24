package com.nery.bustos.mymovies.domain

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.mymovies.data.MovieItemView
import com.nery.bustos.mymovies.data.VideoItemView
import com.nery.bustos.mymovies.presentation.TypeMovie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun fetchMovie(type:TypeMovie): Flow<DataState<List<MovieItemView>>>
    suspend fun fetchVideo(id:Int): Flow<DataState<List<VideoItemView>>>
}