package com.omerfpekgoz.movieapp.domain.repository

import com.omerfpekgoz.movieapp.domain.model.MovieEntity

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
interface MovieRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): List<MovieEntity>
}