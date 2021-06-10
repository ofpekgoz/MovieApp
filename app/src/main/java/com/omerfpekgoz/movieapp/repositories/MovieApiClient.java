package com.omerfpekgoz.movieapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.response.CastResponse;
import com.omerfpekgoz.movieapp.response.CastResult;
import com.omerfpekgoz.movieapp.response.MovieResponse;
import com.omerfpekgoz.movieapp.response.MovieResult;
import com.omerfpekgoz.movieapp.response.PersonMovieCastResponse;
import com.omerfpekgoz.movieapp.response.PersonMovieResult;
import com.omerfpekgoz.movieapp.response.PersonResponse;
import com.omerfpekgoz.movieapp.service.IMovieDao;
import com.omerfpekgoz.movieapp.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiClient {


    private MutableLiveData<List<MovieResponse>> movieListLiveData;
    private MutableLiveData<List<CastResponse>> castListLiveData;
    private MutableLiveData<PersonResponse> personDetailLiveData;
    private MutableLiveData<List<PersonMovieCastResponse>> personMovieLiveData;
    private static MovieApiClient instance;
    private IMovieDao movieDao;
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    public MovieApiClient() {
        movieListLiveData = new MutableLiveData<>();
        castListLiveData = new MutableLiveData<>();
        personDetailLiveData = new MutableLiveData<>();
        personMovieLiveData = new MutableLiveData<>();
        movieDao = APIUtils.getMovieDao();
    }


    public LiveData<List<MovieResponse>> getMovieList() {

        return movieListLiveData;
    }

    //Cast List
    public LiveData<List<CastResponse>> getCastList(int movieId) {
        movieDao.getMovieCast(movieId, APIUtils.API_KEY).enqueue(new Callback<CastResult>() {
            @Override
            public void onResponse(Call<CastResult> call, Response<CastResult> response) {

                if (response.isSuccessful()) {
                    castListLiveData.setValue(response.body().getCast());
                } else {
                    castListLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CastResult> call, Throwable t) {
                castListLiveData.setValue(null);
            }
        });

        return castListLiveData;
    }

    //Person Detail
    public LiveData<PersonResponse> getPersonDetail(int personId) {

        movieDao.getPersonDetail(personId, APIUtils.API_KEY).enqueue(new Callback<PersonResponse>() {
            @Override
            public void onResponse(Call<PersonResponse> call, Response<PersonResponse> response) {
                personDetailLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PersonResponse> call, Throwable t) {
                personDetailLiveData.setValue(null);
            }
        });

        return personDetailLiveData;
    }

    //Person Movie List
    public LiveData<List<PersonMovieCastResponse>> getPersonMovieList(int personId) {
        movieDao.getPersonMovie(personId, APIUtils.API_KEY).enqueue(new Callback<PersonMovieResult>() {
            @Override
            public void onResponse(Call<PersonMovieResult> call, Response<PersonMovieResult> response) {
                Log.e("Response", response.raw().toString());
                if (response.isSuccessful()) {
                    personMovieLiveData.setValue(response.body().getCast());
                } else {

                }
            }

            @Override
            public void onFailure(Call<PersonMovieResult> call, Throwable t) {
                personMovieLiveData.setValue(null);
            }
        });
        return personMovieLiveData;
    }

    //Search Movie
    public void searchMoviesApi(String query, int page) {

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, page);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                myHandler.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);

    }

    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int page;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int page) {
            this.query = query;
            this.page = page;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response response = getMovie(query, page).execute();

                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<MovieResponse> movieList = new ArrayList(((MovieResult) response.body()).getMovieResponse());
                    if (page == 1) {
                        movieListLiveData.postValue(movieList);
                    } else {
                        List<MovieResponse> currentMovies = movieListLiveData.getValue();
                        currentMovies.addAll(movieList);
                        movieListLiveData.postValue(currentMovies);
                    }
                } else {
                    String error = response.errorBody().toString();

                    movieListLiveData.postValue(null);
                }

            } catch (Exception e) {
                e.printStackTrace();
                movieListLiveData.postValue(null);

            }

        }

        //Search Method
        private Call<MovieResult> getMovie(String query, int page) {
            return movieDao.searchMovie(APIUtils.API_KEY, query, page);
        }


        private void cancelRequest() {
            cancelRequest = true;
        }

    }

}
