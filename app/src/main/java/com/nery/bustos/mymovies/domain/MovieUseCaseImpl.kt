package com.nery.bustos.mymovies.domain

import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.utils.Utils.isOnline
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.presentation.ui.MovieItemView
import com.nery.bustos.mymovies.data.MovieRepository
import com.nery.bustos.mymovies.presentation.ui.VideoItemView
import com.nery.bustos.mymovies.data.db.MovieDataBase
import com.nery.bustos.mymovies.data.db.MovieEntity
import com.nery.bustos.mymovies.di.MovieProviderEntryPoint
import com.nery.bustos.mymovies.presentation.TypeMovie
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MovieUseCaseImpl : MovieUseCase {

    private val repository: MovieRepository
    private val db: MovieDataBase


    init {
        val hiltDataBaseEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                MovieProviderEntryPoint.ProviderDataBase::class.java
            )
        db = hiltDataBaseEntryPoint.dataBase()

        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                MovieProviderEntryPoint.ProviderRepository::class.java
            )
        repository = hiltEntryPoint.repository()
    }

    @DelicateCoroutinesApi
    override suspend fun fetchMovie(type: TypeMovie): Flow<DataState<List<MovieItemView>>> = flow {
        with(repository) {
            if (isOnline(App.applicationContext())) {
                fetchMovieRemote(type).collect {
                    when (it) {
                        is DataState.Success -> {
                            emit(DataState.Success(
                                it.data.lsPlayingNow.map { movie ->
                                    movie.toItemView()
                                }
                            ))
                            saveMovies(it.data.lsPlayingNow.map { movie ->
                                movie.toEntity(type.ordinal)
                            })
                        }
                        is DataState.Error -> emit(DataState.Error(it.message))
                        DataState.Loading -> emit(DataState.Loading)
                    }
                }
            } else {
                fetchMovieLocal(type).collect {
                    emit(DataState.Success(
                        it.map { movie ->
                            movie.toItemView()
                        }
                    ))
                }
            }
        }


    }

    override suspend fun fetchVideo(id: Int): Flow<DataState<List<VideoItemView>>> = flow {
        if(isOnline(App.applicationContext()))
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
        else
            emit(DataState.Error("Video not available"))
    }

    @DelicateCoroutinesApi
    override fun saveMovies(movies: List<MovieEntity>) {
        GlobalScope.launch {
            db.movieDao().insertMovie(movies)
        }
    }
}