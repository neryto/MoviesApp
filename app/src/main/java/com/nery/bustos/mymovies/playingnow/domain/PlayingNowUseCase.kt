package com.nery.bustos.mymovies.playingnow.domain

interface PlayingNowUseCase {
    suspend fun fetchPlayingNow()
}