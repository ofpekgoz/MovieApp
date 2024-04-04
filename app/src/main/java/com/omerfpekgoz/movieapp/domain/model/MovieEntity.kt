package com.omerfpekgoz.movieapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
@Entity(tableName = "Movie")
data class MovieEntity(
    @PrimaryKey
    val id: Int?,
    val original_title: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?,
)