package com.nery.bustos.mymovies.data

import com.google.gson.reflect.TypeToken
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.network.ResponseToFlow
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.di.MovieProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl : MovieRepository {

    private val service: MovieApi

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                MovieProviderEntryPoint.ProviderService::class.java
            )
        service = hiltEntryPoint.service()
    }

    override suspend fun fetchPlayingNowRemote(): Flow<DataState<MovieResponse>> {
        val type = object : TypeToken<MovieResponse>() {}.type
        return ResponseToFlow<MovieResponse>()
            .getFlow(service.getPlayingNowList(),type)
    }

    override suspend fun fetchPlayingNowLocal() {

    }

    override suspend fun fetchVideo(id:Int) : Flow<DataState<VideoResponse>> {
        val type = object : TypeToken<VideoResponse>() {}.type
        return ResponseToFlow<VideoResponse>()
            .getFlow(service.getVideo(id),type)
    }
}