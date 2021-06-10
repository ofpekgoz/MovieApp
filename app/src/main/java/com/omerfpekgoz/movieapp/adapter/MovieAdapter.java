package com.omerfpekgoz.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omerfpekgoz.movieapp.R;
import com.omerfpekgoz.movieapp.databinding.CardviewMovieitemBinding;
import com.omerfpekgoz.movieapp.model.Movie;
import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.ui.fragment.MovieListFragmentDirections;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.cardViewHolder> {

    private Context mContext;
    private View view;
    private List<Movie> movieList;
    private CardviewMovieitemBinding movieitemBinding;

    public MovieAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        movieitemBinding = CardviewMovieitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        view = movieitemBinding.getRoot();
        mContext = view.getContext();
        return new MovieAdapter.cardViewHolder(movieitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewHolder holder, int position) {

        Movie movie = movieList.get(position);
        movieitemBinding.txtMovieName.setText(movie.getMovieTitle());
        movieitemBinding.txtMovieOwerview.setText(movie.getMovieOverview());

        //Glide
        Glide.with(mContext).load(APIUtils.IMAGE_URL + movie.getMoviePosterPath()).centerCrop().placeholder(R.drawable.ic_launcher_background).into(movieitemBinding.imgMovie);

        movieitemBinding.cardViewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavDirections action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie.getMovieId());
                Navigation.findNavController(v).navigate(action);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class cardViewHolder extends RecyclerView.ViewHolder {

        CardviewMovieitemBinding movieitemBinding;

        public cardViewHolder(CardviewMovieitemBinding movieitemBinding) {
            super(movieitemBinding.getRoot());
            this.movieitemBinding = movieitemBinding;

        }
    }
}
