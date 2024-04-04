package com.omerfpekgoz.movieapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omerfpekgoz.movieapp.R
import com.omerfpekgoz.movieapp.databinding.FragmentMovieListBinding
import com.omerfpekgoz.movieapp.domain.model.MovieEntity
import com.omerfpekgoz.movieapp.presentation.adapter.MovieListAdapter
import com.omerfpekgoz.movieapp.presentation.viewmodel.MovieViewModel
import com.omerfpekgoz.movieapp.util.Resource
import com.omerfpekgoz.movieapp.util.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var b: FragmentMovieListBinding
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var page = 1
    private val totalPage = 5
    private var isLoading = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentMovieListBinding.inflate(layoutInflater)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapterInitialize()
        setupObserve()
        getMovieList(page)
    }

    fun getMovieList(page: Int) {
        movieViewModel.getMovieList(Util.hasInternetConnection(requireContext()), page)
    }

    private fun setupObserve() {
        lifecycleScope.launch {
            movieViewModel.movieListState.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                        b.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        isLoading = false
                        b.progressBar.visibility = View.GONE
                        movieListAdapter.submitList(it.data as List<MovieEntity>)
                    }

                    is Resource.Error -> {
                        isLoading = true
                        b.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

    }

    private fun movieAdapterInitialize() {
        movieListAdapter = MovieListAdapter()
        b.recyclerViewMovie.adapter = movieListAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        b.recyclerViewMovie.layoutManager = linearLayoutManager
        b.recyclerViewMovie.setHasFixedSize(true)
        b.recyclerViewMovie.parent.requestLayout()

        b.recyclerViewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && page < totalPage && Util.hasInternetConnection(requireContext())) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        page++
                        getMovieList(page)
                    }
                }
            }
        })

        movieListAdapter.onMovieClicked = { movieName ->
            val bundle = bundleOf("movieName" to movieName)
            findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)
        }
    }
}