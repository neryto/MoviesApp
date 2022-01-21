package com.nery.bustos.mymovies.playingnow.domain

import com.nery.bustos.mymovies.playingnow.data.PlayingNowRepository

class PlayingNowUseCaseImpl : PlayingNowUseCase {

    lateinit var repository: PlayingNowRepository

    init {

    }
    override suspend fun fetchPlayingNow() {

    }
}