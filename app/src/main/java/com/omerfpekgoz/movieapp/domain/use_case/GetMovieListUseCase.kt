package com.omerfpekgoz.movieapp.domain.use_case

import com.omerfpekgoz.movieapp.domain.repository.MovieRepository
import com.omerfpekgoz.movieapp.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
class GetMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getMovieList(forceFetchFromRemote: Boolean, page: Int) = flow {
        emit(Resource.Loading)
        try {
            val movieList = movieRepository.getMovieList(forceFetchFromRemote, page)
            emit(Resource.Success(movieList))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Error"))
            return@flow
        }
    }
}