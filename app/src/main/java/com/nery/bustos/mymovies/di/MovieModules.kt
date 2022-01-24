package com.nery.bustos.mymovies.di

import androidx.room.Room
import com.nery.bustos.moviesbasemodule.network.Api
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.data.network.MovieApi
import com.nery.bustos.mymovies.data.MovieRepository
import com.nery.bustos.mymovies.data.MovieRepositoryImpl
import com.nery.bustos.mymovies.data.db.MovieDataBase
import com.nery.bustos.mymovies.domain.MovieUseCase
import com.nery.bustos.mymovies.domain.MovieUseCaseImpl
import com.nery.bustos.mymovies.presentation.MovieView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(FragmentComponent::class)
class MovieViewModule {
    @Provides
    fun movieViewProvider(): MovieView = MovieView()

}


@Module
@InstallIn(SingletonComponent::class)
class MovieUseCaseModule {
    @Provides
    fun movieUseCaseProvider(): MovieUseCase = MovieUseCaseImpl()
}

@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {
    @Provides
    fun movieRepositoryProvider(): MovieRepository = MovieRepositoryImpl()
}

@Module
@InstallIn(SingletonComponent::class)
class MovieServiceModule {
    @Provides
    fun movieServiceProvider(): MovieApi = Api.createApi(MovieApi::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
class MovieDataBaseModule {
    @Provides
    fun movieDataBaseProvider(): MovieDataBase =
        Room.databaseBuilder(
            App.applicationContext(),
            MovieDataBase::class.java,
            "MovieDataBase"
        ).build()

}
