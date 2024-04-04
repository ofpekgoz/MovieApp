package com.omerfpekgoz.movieapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerfpekgoz.movieapp.domain.use_case.GetMovieListUseCase
import com.omerfpekgoz.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private var _movieListState = MutableLiveData<Resource<*>>()
    val movieListState = _movieListState

    fun getMovieList(forceFetchFromRemote: Boolean, page: Int) {
        viewModelScope.launch {
            getMovieListUseCase.getMovieList(forceFetchFromRemote, page).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _movieListState.value = Resource.Loading
                    }

                    is Resource.Success -> {
                        _movieListState.value = Resource.Success(it.data)
                    }

                    is Resource.Error -> {
                        _movieListState.value = Resource.Error(it.message)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}