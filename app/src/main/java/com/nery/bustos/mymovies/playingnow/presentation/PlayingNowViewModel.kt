package com.nery.bustos.mymovies.playingnow.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.presentation.BaseViewModel
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import com.nery.bustos.mymovies.playingnow.data.VideoItemView
import com.nery.bustos.mymovies.playingnow.di.PlayingNowProviderEntryPoint
import com.nery.bustos.mymovies.playingnow.domain.PlayingNowUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PlayingNowViewModel
    : BaseViewModel<List<PlayingNowItemView>>() {

    private var useCase: PlayingNowUseCase
    private val _fetchVideo = MutableLiveData<DataState<List<VideoItemView>>>()
    val fetchVideo: LiveData<DataState<List<VideoItemView>>>
        get() = _fetchVideo


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

    fun fetchVideo(id: Int) {
        viewModelScope.launch {
            useCase.fetchVideo(id).collect {
                _fetchVideo.postValue(it)
            }
        }
    }


}