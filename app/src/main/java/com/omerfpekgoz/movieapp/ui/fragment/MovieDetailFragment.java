package com.omerfpekgoz.movieapp.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.omerfpekgoz.movieapp.R;
import com.omerfpekgoz.movieapp.adapter.CastAdapter;
import com.omerfpekgoz.movieapp.databinding.MovieDetailFragmentBinding;
import com.omerfpekgoz.movieapp.model.Movie;
import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.repositories.MovieRepository;
import com.omerfpekgoz.movieapp.response.CastResponse;
import com.omerfpekgoz.movieapp.response.MovieResponse;
import com.omerfpekgoz.movieapp.viewmodel.MovieDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailFragment extends Fragment {

    private MovieDetailViewModel mViewModel;
    private MovieDetailFragmentBinding movieDetailBinding;
    private Context mContext;
    private int movieId;
    private List<CastResponse> castResponseList;
    private CastAdapter castAdapter;

    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        movieDetailBinding = MovieDetailFragmentBinding.inflate(getLayoutInflater());
        View view = movieDetailBinding.getRoot();
        mContext = view.getContext();

        castResponseList = new ArrayList<>();
        mViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        movieId = getArguments().getInt("movie_id");

        movieDetailBinding.recylerViewCast.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        movieDetailBinding.recylerViewCast.setLayoutManager(layoutManager);

        getMovieByMovieId();
        getCastList();

        return view;
    }


    public void getMovieByMovieId() {

        mViewModel.getMovieByMovieId(APIUtils.API_KEY, movieId).observe(getViewLifecycleOwner(), new Observer<MovieResponse>() {
            @Override
            public void onChanged(MovieResponse movieResponse) {
                Movie movie = new Movie();
                movie.setMovieTitle(movieResponse.getTitle());
                movie.setBackdrop_path(movieResponse.getBackdropPath());
                movie.setMovieVoteAverage(movieResponse.getVoteAverage());
                movie.setMovieOverview(movieResponse.getOverview());

                setMovieData(movie);
            }
        });
    }

    public void setMovieData(Movie movie) {
        movieDetailBinding.txtMovieNameDetail.setText(movie.getMovieTitle());
        movieDetailBinding.txtSummaryDetail.setText(movie.getMovieOverview());
        movieDetailBinding.ratingBarDetail.setRating(movie.getMovieVoteAverage() / 2);

        movieDetailBinding.cardViewMovieDetail.setCardBackgroundColor(Color.TRANSPARENT);
        movieDetailBinding.cardViewMovieDetail.setCardElevation(0);
        //Glide
        Glide.with(mContext).load(APIUtils.IMAGE_URL + movie.getBackdrop_path()).centerCrop().placeholder(R.drawable.ic_launcher_background).into(movieDetailBinding.imgMovieDetail);


    }

    public void getCastList() {
        mViewModel.getCastList(movieId).observe(getViewLifecycleOwner(), new Observer<List<CastResponse>>() {
            @Override
            public void onChanged(List<CastResponse> castResponses) {
                if (castResponses != null) {
                    castResponseList = castResponses;
                    castAdapter = new CastAdapter(mContext, castResponseList);
                    movieDetailBinding.recylerViewCast.setAdapter(castAdapter);
                } else {

                }
            }
        });
    }


}