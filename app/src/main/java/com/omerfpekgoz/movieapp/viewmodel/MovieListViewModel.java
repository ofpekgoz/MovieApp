package com.omerfpekgoz.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.repositories.MovieRepository;
import com.omerfpekgoz.movieapp.response.MovieResponse;
import com.omerfpekgoz.movieapp.response.MovieResult;
import com.omerfpekgoz.movieapp.service.IMovieDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<List<MovieResponse>> popularMovieList;
    private IMovieDao movieDao;

    int mPage;


    public MovieListViewModel() {
        movieRepository = new MovieRepository();
        popularMovieList = new MutableLiveData<>();
        movieDao = APIUtils.getMovieDao();
    }

    public LiveData<List<MovieResponse>> getMovieList() {
        return movieRepository.getMovieList();
    }

    //3)Calling the method in viewmodel

    public void searchMovieApi(String query, int page) {
        movieRepository.searchMovieApi(query, page);
    }

    public void searchNextPage() {
        movieRepository.searchNextPage();
    }

    public LiveData<List<MovieResponse>> getPopularMovie(int page) {
        mPage = page;
        movieDao.getpopularMovie(APIUtils.API_KEY, page).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult movieResult = response.body();
                List<MovieResponse> movieResponseList = movieResult.getMovieResponse();
                popularMovieList.postValue(movieResponseList);

            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });

        return popularMovieList;
    }

    public void getPopularMovieNextPage() {
        getPopularMovie(mPage + 1);
    }
}