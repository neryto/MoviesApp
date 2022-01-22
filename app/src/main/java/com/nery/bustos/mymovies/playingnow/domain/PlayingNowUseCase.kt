package com.nery.bustos.mymovies.playingnow.domain

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import kotlinx.coroutines.flow.Flow

interface PlayingNowUseCase {
    suspend fun fetchPlayingNow(): Flow<DataState<List<PlayingNowItemView>>>
}