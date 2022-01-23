package com.nery.bustos.mymovies.data

import com.nery.bustos.moviesbasemodule.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchPlayingNowRemote() : Flow<DataState<MovieResponse>>
    suspend fun fetchPlayingNowLocal()
    suspend fun fetchVideo(id:Int) : Flow<DataState<VideoResponse>>
}