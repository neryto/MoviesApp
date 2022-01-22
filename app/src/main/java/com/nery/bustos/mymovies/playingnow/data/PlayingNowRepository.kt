package com.nery.bustos.mymovies.playingnow.data

import com.nery.bustos.moviesbasemodule.DataState
import kotlinx.coroutines.flow.Flow

interface PlayingNowRepository {
    suspend fun fetchPlayingNowRemote() : Flow<DataState<PlayingNowResponse>>
    suspend fun fetchPlayingNowLocal()
}