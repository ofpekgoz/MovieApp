package com.omerfpekgoz.movieapp.data.data_source.remote

import com.omerfpekgoz.movieapp.data.model.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
interface MovieApi {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "d535cd0cabd728c89dd1d92217031eec"
    }
}