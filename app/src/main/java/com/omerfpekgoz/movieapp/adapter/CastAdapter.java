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
import com.omerfpekgoz.movieapp.databinding.CardviewCastitemBinding;
import com.omerfpekgoz.movieapp.databinding.CardviewMovieitemBinding;
import com.omerfpekgoz.movieapp.model.Movie;
import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.response.CastResponse;
import com.omerfpekgoz.movieapp.ui.fragment.MovieDetailFragmentDirections;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.cardViewHolder> {

    private Context mContext;
    private View view;
    private List<CastResponse> castResponseList;
    CardviewCastitemBinding castitemBinding;

    public CastAdapter(Context mContext, List<CastResponse> castResponseList) {
        this.mContext = mContext;
        this.castResponseList = castResponseList;
    }

    @NonNull
    @Override
    public cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        castitemBinding = CardviewCastitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        view = castitemBinding.getRoot();
        mContext = view.getContext();
        return new CastAdapter.cardViewHolder(castitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewHolder holder, int position) {

        CastResponse castResponse = castResponseList.get(position);
        castitemBinding.txtPersonName.setText(castResponse.getName());
        Glide.with(mContext).load(APIUtils.IMAGE_URL + castResponse.getProfilePath()).centerCrop()
                .placeholder(R.drawable.ic_launcher_background).into(castitemBinding.imageView2);


        holder.castitemBinding.cardViewCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = MovieDetailFragmentDirections.actionMovieDetailFragmentToCastPersonDetailFragment(castResponse.getId());
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return castResponseList.size();
    }


    class cardViewHolder extends RecyclerView.ViewHolder {

        CardviewCastitemBinding castitemBinding;

        public cardViewHolder(CardviewCastitemBinding castitemBinding) {
            super(castitemBinding.getRoot());
            this.castitemBinding = castitemBinding;

        }
    }
}
