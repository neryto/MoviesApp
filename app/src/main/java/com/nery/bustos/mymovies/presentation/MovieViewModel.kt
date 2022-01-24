package com.nery.bustos.mymovies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.presentation.BaseViewModel
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.TypeMovie
import com.nery.bustos.mymovies.presentation.ui.MovieItemView
import com.nery.bustos.mymovies.presentation.ui.VideoItemView
import com.nery.bustos.mymovies.di.MovieProviderEntryPoint
import com.nery.bustos.mymovies.domain.MovieUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MovieViewModel
    : BaseViewModel<List<MovieItemView>>() {

    private var useCase: MovieUseCase
    private val _fetchVideo = MutableLiveData<DataState<List<VideoItemView>>>()
    val fetchVideo: LiveData<DataState<List<VideoItemView>>>
        get() = _fetchVideo


    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                MovieProviderEntryPoint.ProviderUseCase::class.java
            )
        useCase = hiltEntryPoint.useCase()
    }


    fun fetchMovie(type: TypeMovie) {
        viewModelScope.launch {
            useCase.fetchMovie(type).collect {
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