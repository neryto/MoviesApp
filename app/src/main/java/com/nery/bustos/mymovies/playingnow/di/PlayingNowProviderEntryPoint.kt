package com.nery.bustos.mymovies.playingnow.di

import com.nery.bustos.mymovies.playingnow.data.PlayingNowApi
import com.nery.bustos.mymovies.playingnow.data.PlayingNowRepository
import com.nery.bustos.mymovies.playingnow.domain.PlayingNowUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


class PlayingNowProviderEntryPoint {
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderUseCase {
        fun useCase(): PlayingNowUseCase
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderRepository {
        fun repository(): PlayingNowRepository
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderService {
        fun service(): PlayingNowApi
    }

}