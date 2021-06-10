package com.omerfpekgoz.movieapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omerfpekgoz.movieapp.R;
import com.omerfpekgoz.movieapp.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {

    private static int DELAY = 2500;
    private FragmentSplashBinding fragmentSplashBinding;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSplashBinding = FragmentSplashBinding.inflate(getLayoutInflater());
        View view = fragmentSplashBinding.getRoot();
        mContext = view.getContext();


        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NavDirections action = SplashFragmentDirections.actionSplashFragmentToMovieListFragment();
                            Navigation.findNavController(view).navigate(action);
                        }
                    });
                }
            }
        };
        timerThread.start();

        return view;

    }

}