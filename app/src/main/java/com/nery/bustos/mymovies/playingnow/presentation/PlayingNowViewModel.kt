package com.nery.bustos.mymovies.playingnow.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nery.bustos.moviesbasemodule.presentation.BaseViewModel
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.playingnow.di.PlayingNowProviderEntryPoint
import com.nery.bustos.mymovies.playingnow.domain.PlayingNowUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch


class PlayingNowViewModel
    : BaseViewModel<Nothing>() {

    var useCase: PlayingNowUseCase

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(App.applicationContext(), PlayingNowProviderEntryPoint::class.java)
        useCase = hiltEntryPoint.useCase()
    }


    fun fetchPlayingNow() {
        viewModelScope.launch {
            useCase.fetchPlayingNow()
        }
    }


}