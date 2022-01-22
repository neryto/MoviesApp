package com.nery.bustos.mymovies.playingnow.presentation

import androidx.lifecycle.viewModelScope
import com.nery.bustos.moviesbasemodule.presentation.BaseViewModel
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import com.nery.bustos.mymovies.playingnow.di.PlayingNowProviderEntryPoint
import com.nery.bustos.mymovies.playingnow.domain.PlayingNowUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class PlayingNowViewModel
    : BaseViewModel<List<PlayingNowItemView>>() {

    var useCase: PlayingNowUseCase

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                PlayingNowProviderEntryPoint.ProviderUseCase::class.java
            )
        useCase = hiltEntryPoint.useCase()
    }


    fun fetchPlayingNow() {
        viewModelScope.launch {
            useCase.fetchPlayingNow().collect {
                _fetchInfo.postValue(it)
            }
        }
    }


}