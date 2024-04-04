package com.omerfpekgoz.movieapp.data.data_source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omerfpekgoz.movieapp.data.data_source.local.dao.MovieDao
import com.omerfpekgoz.movieapp.domain.model.MovieEntity

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        const val DATABASE_NAME = "movie_db"
    }
}