package com.omerfpekgoz.movieapp.di

import android.app.Application
import androidx.room.Room
import com.omerfpekgoz.movieapp.data.data_source.local.dao.MovieDao
import com.omerfpekgoz.movieapp.data.data_source.local.database.MovieDatabase
import com.omerfpekgoz.movieapp.data.data_source.local.database.MovieDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun providesMovieDAO(
        movieDatabase: MovieDatabase
    ): MovieDao =
        movieDatabase.movieDao
}