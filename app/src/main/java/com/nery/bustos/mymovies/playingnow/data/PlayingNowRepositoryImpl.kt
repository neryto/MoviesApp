package com.nery.bustos.mymovies.playingnow.data

import com.google.gson.reflect.TypeToken
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.network.ResponseToFlow
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.playingnow.di.PlayingNowProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow

class PlayingNowRepositoryImpl : PlayingNowRepository {

    private val service: PlayingNowApi

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                PlayingNowProviderEntryPoint.ProviderService::class.java
            )
        service = hiltEntryPoint.service()
    }

    override suspend fun fetchPlayingNowRemote(): Flow<DataState<PlayingNowResponse>> {
        val type = object : TypeToken<PlayingNowResponse>() {}.type

        return ResponseToFlow<PlayingNowResponse>()
            .getFlow(service.getPlayingNowList(),type)

    }

    override suspend fun fetchPlayingNowLocal() {

    }
}