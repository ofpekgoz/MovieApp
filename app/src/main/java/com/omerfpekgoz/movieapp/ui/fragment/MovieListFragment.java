package com.omerfpekgoz.movieapp.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.omerfpekgoz.movieapp.R;
import com.omerfpekgoz.movieapp.adapter.MovieAdapter;
import com.omerfpekgoz.movieapp.databinding.MovieListFragmentBinding;
import com.omerfpekgoz.movieapp.model.Movie;
import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.response.MovieResponse;
import com.omerfpekgoz.movieapp.service.IMovieDao;
import com.omerfpekgoz.movieapp.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment {

    private MovieListViewModel mViewModel;
    private MovieAdapter movieAdapter;
    private Context mContext;
    private MovieListFragmentBinding movieListBinding;
    private IMovieDao movieDao;
    private List<MovieResponse> movieResponseList;
    private List<Movie> movieList;
    private List<Movie> popularMovieList;

    String searchMovieTitle;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        movieListBinding = MovieListFragmentBinding.inflate(getLayoutInflater());
        View view = movieListBinding.getRoot();
        mContext = view.getContext();

        movieDao = APIUtils.getMovieDao();
        movieResponseList = new ArrayList<>();
        movieList = new ArrayList<>();
        popularMovieList = new ArrayList<>();
        mViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);


        init();
        getPopularMovie();
        searchMovie();


        movieListBinding.txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                searchMovieTitle = movieListBinding.txtSearch.getText().toString();

                Log.e("Search Movie", searchMovieTitle.toString());
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    movieList.clear();
                    searchMovieApi(searchMovieTitle, 1);

                }
                return false;
            }
        });

        return view;
    }

    public void init() {
        movieListBinding.recylerViewMovie.setHasFixedSize(true);
        movieListBinding.recylerViewMovie.setLayoutManager(new LinearLayoutManager(mContext));


        movieListBinding.recylerViewMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                 if (movieListBinding.recylerViewMovie.canScrollVertically(1)){
//                        movieListFragmentViewModel.getPopularMovieNextPage();
//                 }

            }
        });

    }


    //Search Movie Method
    public void searchMovie() {

        mViewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<List<MovieResponse>>() {
            @Override
            public void onChanged(List<MovieResponse> movies) {

                //Observing for any data change
                if (movies != null) {
                    for (MovieResponse movieResponse : movies) {
                        //Get data

                        Movie movie = new Movie();
                        movie.setMovieId(movieResponse.getId());
                        movie.setMovieOverview(movieResponse.getOverview());
                        movie.setMovieTitle(movieResponse.getTitle());
                        movie.setMoviePosterPath(movieResponse.getPosterPath());
                        movie.setMovieReleaseDate(movieResponse.getReleaseDate());
                        movie.setMovieVoteAverage(movieResponse.getVoteAverage());
                        movie.setOriginal_language(movieResponse.getOriginalLanguage());

                        movieList.add(movie);

                    }

                    movieAdapter = new MovieAdapter(mContext, movieList);
                    movieListBinding.recylerViewMovie.setAdapter(movieAdapter);

                }

            }
        });
    }


    //Get Popular Movie Method
    public void getPopularMovie() {

        mViewModel.getPopularMovie(1).observe(getViewLifecycleOwner(), new Observer<List<MovieResponse>>() {
            @Override
            public void onChanged(List<MovieResponse> movieResponses) {

                if (movieResponses != null) {
                    for (MovieResponse movieResponse : movieResponses) {
                        Movie movie = new Movie();

                        movie.setMovieId(movieResponse.getId());
                        movie.setMovieOverview(movieResponse.getOverview());
                        movie.setMovieTitle(movieResponse.getTitle());
                        movie.setMoviePosterPath(movieResponse.getPosterPath());
                        movie.setMovieReleaseDate(movieResponse.getReleaseDate());
                        movie.setMovieVoteAverage(movieResponse.getVoteAverage());
                        movie.setOriginal_language(movieResponse.getOriginalLanguage());

                        popularMovieList.add(movie);

                    }
                    movieAdapter = new MovieAdapter(mContext, popularMovieList);
                    movieListBinding.recylerViewMovie.setAdapter(movieAdapter);

                }
            }
        });


    }


    //Search Movie
    private void searchMovieApi(String query, int page) {
        mViewModel.searchMovieApi(query, page);
    }

}