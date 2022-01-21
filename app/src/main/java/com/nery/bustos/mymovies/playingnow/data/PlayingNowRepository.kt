package com.nery.bustos.mymovies.playingnow.data

interface PlayingNowRepository {
    suspend fun fetchPlayingNowRemote()
}