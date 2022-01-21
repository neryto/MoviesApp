package com.nery.bustos.mymovies.playingnow.di

import com.nery.bustos.mymovies.playingnow.domain.PlayingNowUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PlayingNowProviderEntryPoint {
    fun useCase(): PlayingNowUseCase
}