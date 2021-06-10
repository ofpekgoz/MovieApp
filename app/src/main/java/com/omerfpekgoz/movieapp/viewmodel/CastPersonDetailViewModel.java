package com.omerfpekgoz.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.omerfpekgoz.movieapp.repositories.MovieRepository;
import com.omerfpekgoz.movieapp.response.PersonMovieCastResponse;
import com.omerfpekgoz.movieapp.response.PersonResponse;

import java.util.List;

public class CastPersonDetailViewModel extends ViewModel {


    public MovieRepository movieRepository;

    public CastPersonDetailViewModel() {
        movieRepository = new MovieRepository();
    }

    public LiveData<PersonResponse> getPersonDetail(int personId) {
        return movieRepository.getPersonDetail(personId);
    }

    public LiveData<List<PersonMovieCastResponse>> getPersonMovieList(int personId) {
        return movieRepository.getPersonMovieList(personId);
    }

}