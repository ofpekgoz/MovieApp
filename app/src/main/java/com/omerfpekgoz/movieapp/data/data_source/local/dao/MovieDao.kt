package com.omerfpekgoz.movieapp.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.omerfpekgoz.movieapp.domain.model.MovieEntity

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM Movie")
    suspend fun getMovieList(): List<MovieEntity>
}