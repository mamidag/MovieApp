package com.example.movieapp.ui.moviedetail.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.model.detail.MovieDetail
import com.example.movieapp.api.model.movie.Movie
import com.example.movieapp.data.Resource
import com.example.movieapp.repository.GenresRepository
import com.example.movieapp.repository.MainRepo
import com.example.movieapp.repository.MovieDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MovieDetailViewModel @ViewModelInject constructor(
    private val detailRepository: MovieDetailRepository
) : ViewModel() {

    val movieDetail: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()


    fun fetchMovieDetail(movieId: Int): LiveData<Resource<MovieDetail>> {
        return if (movieDetail.value?.data == null) {
            viewModelScope.launch {
                detailRepository.fetchMovieDetail(movieId = movieId).collect {
                    movieDetail.postValue(it)
                }
            }
            movieDetail
        } else {
            movieDetail
        }
    }

}