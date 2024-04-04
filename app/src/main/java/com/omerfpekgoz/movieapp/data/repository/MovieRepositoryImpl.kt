package com.omerfpekgoz.movieapp.data.repository

import com.omerfpekgoz.movieapp.data.data_source.local.dao.MovieDao
import com.omerfpekgoz.movieapp.data.data_source.remote.MovieApi
import com.omerfpekgoz.movieapp.data.model.toMovieEntity
import com.omerfpekgoz.movieapp.domain.model.MovieEntity
import com.omerfpekgoz.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi, private val movieDao: MovieDao
) : MovieRepository {
    override suspend fun getMovieList(forceFetchFromRemote: Boolean, page: Int)
            : List<MovieEntity> {
        val localMovieList = movieDao.getMovieList()
        val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote
        if (shouldLoadLocalMovie) {
            return localMovieList
        }
        return try {
            val getMovieFromApi = movieApi.getMovieList(page).results
            val saveMovieList = getMovieFromApi.map { movieDTO ->
                movieDTO.toMovieEntity()
            }
            movieDao.upsertMovieList(saveMovieList)
            saveMovieList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}