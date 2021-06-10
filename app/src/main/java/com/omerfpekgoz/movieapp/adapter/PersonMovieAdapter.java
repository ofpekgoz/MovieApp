package com.omerfpekgoz.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omerfpekgoz.movieapp.R;
import com.omerfpekgoz.movieapp.databinding.CardviewPersonmovieitemBinding;
import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.response.PersonMovieCastResponse;

import java.util.List;

public class PersonMovieAdapter extends RecyclerView.Adapter<PersonMovieAdapter.cardViewHolder> {

    private Context mContext;
    private List<PersonMovieCastResponse> personMovieCastResponseList;
    private CardviewPersonmovieitemBinding personmovieitemBinding;
    private View view;

    public PersonMovieAdapter(Context mContext, List<PersonMovieCastResponse> personMovieCastResponseList) {
        this.mContext = mContext;
        this.personMovieCastResponseList = personMovieCastResponseList;
    }

    @NonNull
    @Override
    public cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        personmovieitemBinding = CardviewPersonmovieitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        view = personmovieitemBinding.getRoot();
        mContext = view.getContext();
        return new PersonMovieAdapter.cardViewHolder(personmovieitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewHolder holder, int position) {

        PersonMovieCastResponse personMovieCastResponse = personMovieCastResponseList.get(position);
        Glide.with(mContext).load(APIUtils.IMAGE_URL + personMovieCastResponse.getPosterPath()).centerCrop()
                .placeholder(R.drawable.ic_launcher_background).into(personmovieitemBinding.imgPersonMovie);


    }

    @Override
    public int getItemCount() {
        return personMovieCastResponseList.size();
    }

    class cardViewHolder extends RecyclerView.ViewHolder {

        CardviewPersonmovieitemBinding personmovieitemBinding;

        public cardViewHolder(CardviewPersonmovieitemBinding personmovieitemBinding) {
            super(personmovieitemBinding.getRoot());
            this.personmovieitemBinding = personmovieitemBinding;

        }
    }
}
