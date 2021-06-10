package com.omerfpekgoz.movieapp.repositories;

import androidx.lifecycle.LiveData;


import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.response.CastResponse;
import com.omerfpekgoz.movieapp.response.MovieResponse;
import com.omerfpekgoz.movieapp.response.PersonMovieCastResponse;
import com.omerfpekgoz.movieapp.response.PersonResponse;
import com.omerfpekgoz.movieapp.service.IMovieDao;

import java.util.List;


public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;
    private IMovieDao movieDao;

    private String mQuery;
    private int mPage;

    public MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
        movieDao = APIUtils.getMovieDao();
    }


    //Get Movie List
    public LiveData<List<MovieResponse>> getMovieList() {
        return movieApiClient.getMovieList();
    }

    //Get Cast List
    public LiveData<List<CastResponse>> getCastList(int movieId) {
        return movieApiClient.getCastList(movieId);
    }

    //Get Person Detail
    public LiveData<PersonResponse> getPersonDetail(int personId) {
        return movieApiClient.getPersonDetail(personId);
    }

    //Get Person Movie List
    public LiveData<List<PersonMovieCastResponse>> getPersonMovieList(int personId) {
        return movieApiClient.getPersonMovieList(personId);
    }


    //Search Movie
    public void searchMovieApi(String query, int page) {
        mQuery = query;
        mPage = page;
        movieApiClient.searchMoviesApi(query, page);

    }

    public void searchNextPage() {
        searchMovieApi(mQuery, mPage + 1);
    }


}
