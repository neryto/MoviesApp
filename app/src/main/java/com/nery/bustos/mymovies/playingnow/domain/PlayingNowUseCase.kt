package com.nery.bustos.mymovies.playingnow.domain

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import com.nery.bustos.mymovies.playingnow.data.VideoItemView
import kotlinx.coroutines.flow.Flow

interface PlayingNowUseCase {
    suspend fun fetchPlayingNow(): Flow<DataState<List<PlayingNowItemView>>>
    suspend fun fetchVideo(id:Int): Flow<DataState<List<VideoItemView>>>
}