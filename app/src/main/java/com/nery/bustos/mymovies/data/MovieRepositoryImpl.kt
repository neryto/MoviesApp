package com.nery.bustos.mymovies.data

import com.google.gson.reflect.TypeToken
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.network.ResponseToFlow
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.data.db.MovieDataBase
import com.nery.bustos.mymovies.data.db.MovieEntity
import com.nery.bustos.mymovies.data.network.MovieApi
import com.nery.bustos.mymovies.data.network.MovieResponse
import com.nery.bustos.mymovies.data.network.VideoResponse
import com.nery.bustos.mymovies.di.MovieProviderEntryPoint
import com.nery.bustos.mymovies.presentation.TypeMovie
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl : MovieRepository {

    private val service: MovieApi
    private val db: MovieDataBase

    init {
        val hiltServiceEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                MovieProviderEntryPoint.ProviderService::class.java
            )

        val hiltDataBaseEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                MovieProviderEntryPoint.ProviderDataBase::class.java
            )

        service = hiltServiceEntryPoint.service()
        db = hiltDataBaseEntryPoint.dataBase()
    }


    override suspend fun fetchMovieRemote(typeMovie: TypeMovie)
            : Flow<DataState<MovieResponse>> {
        val type = object : TypeToken<MovieResponse>() {}.type
        val path: String = when (typeMovie) {
            TypeMovie.PLAYING_NOW -> "now_playing"
            TypeMovie.MOST_POPULAR -> "popular"
        }
        return ResponseToFlow<MovieResponse>()
            .getFlow(service.getMovieList(path), type)

    }

    override suspend fun fetchMovieLocal(typeMovie: TypeMovie)
            : Flow<List<MovieEntity>> {
        return when (typeMovie) {
            TypeMovie.PLAYING_NOW -> db.movieDao().getPlayingNow()
            TypeMovie.MOST_POPULAR -> db.movieDao().getMostPopular()
        }
    }

    override suspend fun fetchVideo(id: Int): Flow<DataState<VideoResponse>> {
        val type = object : TypeToken<VideoResponse>() {}.type
        return ResponseToFlow<VideoResponse>()
            .getFlow(service.getVideo(id), type)
    }
}