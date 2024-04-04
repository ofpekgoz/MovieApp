package com.omerfpekgoz.movieapp.di

import com.omerfpekgoz.movieapp.data.repository.MovieRepositoryImpl
import com.omerfpekgoz.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        repositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}