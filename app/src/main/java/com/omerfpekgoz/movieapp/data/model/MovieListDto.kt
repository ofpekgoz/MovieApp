package com.omerfpekgoz.movieapp.data.model

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)