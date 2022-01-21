package com.nery.bustos.mymovies.playingnow.di

import com.nery.bustos.mymovies.playingnow.domain.PlayingNowUseCase
import com.nery.bustos.mymovies.playingnow.domain.PlayingNowUseCaseImpl
import com.nery.bustos.mymovies.playingnow.presentation.PlayingNowView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(FragmentComponent::class)
class PlayingNowViewModule {
    @Provides
    fun playingNowViewProvider() : PlayingNowView = PlayingNowView()

}


@Module
@InstallIn(SingletonComponent::class)
class PlayingNowUseCaseModule{
    @Provides
    fun playingNowUseCaseProvider() : PlayingNowUseCase = PlayingNowUseCaseImpl()
}
