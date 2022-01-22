package com.nery.bustos.mymovies.playingnow.domain

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.utils.Utils.isOnline
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import com.nery.bustos.mymovies.playingnow.data.PlayingNowRepository
import com.nery.bustos.mymovies.playingnow.di.PlayingNowProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class PlayingNowUseCaseImpl : PlayingNowUseCase {

    var repository: PlayingNowRepository

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                PlayingNowProviderEntryPoint.ProviderRepository::class.java
            )
        repository = hiltEntryPoint.repository()
    }

    override suspend fun fetchPlayingNow(): Flow<DataState<List<PlayingNowItemView>>> = flow {
        with(repository) {
            if (isOnline(App.applicationContext())) {
                fetchPlayingNowRemote().collect {
                    when (it) {
                        is DataState.Success -> emit(DataState.Success(
                            it.data.lsPlayingNow.map { ls ->
                                ls.toItemView()
                            }
                        ))
                        is DataState.Error -> emit(DataState.Error(it.message))
                        DataState.Loading -> emit(DataState.Loading)
                    }
                }
            } else fetchPlayingNowRemote()
        }


    }
}