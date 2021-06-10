package com.omerfpekgoz.movieapp.service;


import com.omerfpekgoz.movieapp.response.CastResult;
import com.omerfpekgoz.movieapp.response.MovieResponse;
import com.omerfpekgoz.movieapp.response.MovieResult;
import com.omerfpekgoz.movieapp.response.PersonMovieResult;
import com.omerfpekgoz.movieapp.response.PersonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMovieDao {

    // https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    //Serach Movie
    @GET("3/search/movie")
    Call<MovieResult> searchMovie(@Query("api_key") String api_key,
                                  @Query("query") String query,
                                  @Query("page") int page);


    //https://api.themoviedb.org/3/movie/550?api_key=0307c4edab2bfd30c576d9972a43bf2a
    //Search Moview By Moview Id
    @GET("3/movie/{movie_id}?")
    Call<MovieResponse> getMovieByMovieId(@Path("movie_id") int movie_id,
                                          @Query("api_key") String api_key);

    //Popular Movie
    @GET("3/movie/popular")
    Call<MovieResult> getpopularMovie(@Query("api_key") String api_key,
                                      @Query("page") int page);


    //https://api.themoviedb.org/3/movie/2/credits?api_key=0307c4edab2bfd30c576d9972a43bf2a&language=en-US
    //Movie Cast
    @GET("3/movie/{movie_id}/credits")
    Call<CastResult> getMovieCast(@Path("movie_id") int movie_id,
                                  @Query("api_key") String api_key);

    //https://api.themoviedb.org/3/person/200/movie_credits?api_key=0307c4edab2bfd30c576d9972a43bf2a&language=en-US
    //Person Movie
    @GET("3/person/{person_id}/movie_credits")
    Call<PersonMovieResult> getPersonMovie(@Path("person_id") int person_id,
                                           @Query("api_key") String api_key);


    //https://api.themoviedb.org/3/person/1?api_key=0307c4edab2bfd30c576d9972a43bf2a&language=en-US
    //Person Detail
    @GET("3/person/{person_id}?")
    Call<PersonResponse> getPersonDetail(@Path("person_id") int person_id,
                                         @Query("api_key") String api_key);


}
