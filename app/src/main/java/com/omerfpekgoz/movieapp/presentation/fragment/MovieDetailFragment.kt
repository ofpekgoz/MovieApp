package com.omerfpekgoz.movieapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omerfpekgoz.movieapp.R
import com.omerfpekgoz.movieapp.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {

    private lateinit var b: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentMovieDetailBinding.inflate(layoutInflater)
        b.txtMovieName.text = arguments?.getString("movieName")
        return b.root
    }
}