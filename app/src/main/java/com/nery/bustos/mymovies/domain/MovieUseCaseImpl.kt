package com.nery.bustos.mymovies.domain

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.utils.Utils.isOnline
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.data.MovieItemView
import com.nery.bustos.mymovies.data.MovieRepository
import com.nery.bustos.mymovies.data.VideoItemView
import com.nery.bustos.mymovies.di.MovieProviderEntryPoint
import com.nery.bustos.mymovies.presentation.TypeMovie
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MovieUseCaseImpl : MovieUseCase {

    var repository: MovieRepository

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                MovieProviderEntryPoint.ProviderRepository::class.java
            )
        repository = hiltEntryPoint.repository()
    }

    override suspend fun fetchMovie(type: TypeMovie): Flow<DataState<List<MovieItemView>>> = flow {
        with(repository) {
            if (isOnline(App.applicationContext())) {
                fetchMovieRemote(type).collect {
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
            } else fetchMovieLocal()
        }


    }

    override suspend fun fetchVideo(id: Int): Flow<DataState<List<VideoItemView>>> = flow {
        repository.fetchVideo(id).collect {
            when (it) {
                is DataState.Success -> {
                    emit(DataState.Success(
                        it.data.lsVideos.filter { video ->
                            video.site == "YouTube"
                        }.map { videoYoutube ->
                            videoYoutube.toVideoItemView()
                        }
                    )
                    )
                }
                is DataState.Error -> emit(DataState.Error(it.message))
                DataState.Loading -> emit(DataState.Loading)
            }
        }
    }
}