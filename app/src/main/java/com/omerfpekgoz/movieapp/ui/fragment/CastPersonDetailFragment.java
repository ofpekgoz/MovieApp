package com.omerfpekgoz.movieapp.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.omerfpekgoz.movieapp.R;
import com.omerfpekgoz.movieapp.adapter.PersonMovieAdapter;
import com.omerfpekgoz.movieapp.databinding.CastPersonDetailFragmentBinding;
import com.omerfpekgoz.movieapp.network.APIUtils;
import com.omerfpekgoz.movieapp.response.PersonMovieCastResponse;
import com.omerfpekgoz.movieapp.response.PersonResponse;
import com.omerfpekgoz.movieapp.viewmodel.CastPersonDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class CastPersonDetailFragment extends Fragment {

    private CastPersonDetailViewModel mViewModel;
    private CastPersonDetailFragmentBinding castPersonDetailFragmentBinding;
    private Context mContext;
    private int personId;
    private PersonMovieAdapter personMovieAdapter;
    private List<PersonMovieCastResponse> personMovieCastResponseList;

    public static CastPersonDetailFragment newInstance() {
        return new CastPersonDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        castPersonDetailFragmentBinding = CastPersonDetailFragmentBinding.inflate(getLayoutInflater());
        View view = castPersonDetailFragmentBinding.getRoot();
        mContext = view.getContext();
        mViewModel = new ViewModelProvider(this).get(CastPersonDetailViewModel.class);

        personMovieCastResponseList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        castPersonDetailFragmentBinding.recylerViewPersonMovie.setHasFixedSize(true);
        castPersonDetailFragmentBinding.recylerViewPersonMovie.setLayoutManager(layoutManager);

        personId = getArguments().getInt("person_id");
        getPersonDetail();
        getPersonMovie();
        return view;
    }

    public void getPersonDetail() {
        mViewModel.getPersonDetail(personId).observe(getViewLifecycleOwner(), new Observer<PersonResponse>() {
            @Override
            public void onChanged(PersonResponse personResponse) {
                if (personResponse != null) {
                    castPersonDetailFragmentBinding.txtPersonNameDetail.setText(personResponse.getName());
                    castPersonDetailFragmentBinding.txtPersonDepartman.setText(personResponse.getKnownForDepartment());
                    castPersonDetailFragmentBinding.txtBiographyDetail.setText(personResponse.getBiography());
                    Glide.with(mContext).load(APIUtils.IMAGE_URL + personResponse.getProfilePath()).centerCrop().
                            placeholder(R.drawable.ic_launcher_background).into(castPersonDetailFragmentBinding.imgPersonDetail);

                } else {

                }
            }
        });
    }

    public void getPersonMovie() {
        mViewModel.getPersonMovieList(personId).observe(getViewLifecycleOwner(), new Observer<List<PersonMovieCastResponse>>() {
            @Override
            public void onChanged(List<PersonMovieCastResponse> personMovieCastResponses) {
                if (personMovieCastResponses != null) {
                    personMovieCastResponseList = personMovieCastResponses;
                    personMovieAdapter = new PersonMovieAdapter(mContext, personMovieCastResponseList);
                    castPersonDetailFragmentBinding.recylerViewPersonMovie.setAdapter(personMovieAdapter);
                } else {

                }
            }
        });
    }

}