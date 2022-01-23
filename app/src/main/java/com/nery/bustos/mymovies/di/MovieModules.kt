package com.nery.bustos.mymovies.di

import com.nery.bustos.moviesbasemodule.network.Api
import com.nery.bustos.mymovies.data.MovieApi
import com.nery.bustos.mymovies.data.MovieRepository
import com.nery.bustos.mymovies.data.MovieRepositoryImpl
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
class PlayingNowViewModule {
    @Provides
    fun playingNowViewProvider() : MovieView = MovieView()

}


@Module
@InstallIn(SingletonComponent::class)
class PlayingNowUseCaseModule{
    @Provides
    fun playingNowUseCaseProvider() : MovieUseCase = MovieUseCaseImpl()
}

@Module
@InstallIn(SingletonComponent::class)
class PlayingNowRepositoryModule{
    @Provides
    fun playingNowRepositoryProvider() : MovieRepository = MovieRepositoryImpl()
}

@Module
@InstallIn(SingletonComponent::class)
class PlayingNowServiceModule{
    @Provides
    fun playingNowServiceProvider() : MovieApi = Api.createApi(MovieApi::class.java)

}
