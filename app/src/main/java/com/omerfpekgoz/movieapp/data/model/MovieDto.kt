package com.omerfpekgoz.movieapp.data.model

import com.omerfpekgoz.movieapp.domain.model.MovieEntity

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
data class MovieDto(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        original_title = original_title,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average
    )
}