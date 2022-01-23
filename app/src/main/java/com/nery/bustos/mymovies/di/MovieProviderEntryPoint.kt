package com.nery.bustos.mymovies.di

import com.nery.bustos.mymovies.data.MovieApi
import com.nery.bustos.mymovies.data.MovieRepository
import com.nery.bustos.mymovies.domain.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


class MovieProviderEntryPoint {
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderUseCase {
        fun useCase(): MovieUseCase
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderRepository {
        fun repository(): MovieRepository
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderService {
        fun service(): MovieApi
    }

}