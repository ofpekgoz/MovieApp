package com.omerfpekgoz.movieapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.repositories.MovieRepository;
import com.omerfpekgoz.movieapp.response.CastResponse;
import com.omerfpekgoz.movieapp.response.MovieResponse;
import com.omerfpekgoz.movieapp.service.IMovieDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieResponse> movieResponseLiveData;
    private MutableLiveData<List<CastResponse>> castListLiveData;
    IMovieDao movieDao;
    private MovieRepository movieRepository;

    public MovieDetailViewModel() {
        movieResponseLiveData = new MutableLiveData<>();
        movieDao = APIUtils.getMovieDao();
        movieRepository = new MovieRepository();
    }

    public LiveData<MovieResponse> getMovieByMovieId(String api_key, int movie_id) {
        movieDao.getMovieByMovieId(movie_id, api_key).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                MovieResponse movieResponse = response.body();
                movieResponseLiveData.postValue(movieResponse);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return movieResponseLiveData;
    }

    public LiveData<List<CastResponse>> getCastList(int movieId) {
        Log.e("ÇAlıştı", "1");
        return movieRepository.getCastList(movieId);
    }
}