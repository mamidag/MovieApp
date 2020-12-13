package com.example.movieapp.ui.movielist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.movieapp.api.model.genres.Genres
import com.example.movieapp.api.model.movie.Movie
import com.example.movieapp.data.Resource
import com.example.movieapp.repository.GenresRepository
import com.example.movieapp.repository.MainRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MovieListViewModel @ViewModelInject constructor(
    private val movieListRepository: MainRepo,
    private val genresRepository: GenresRepository
) : ViewModel() {

    val movieList: MutableLiveData<Resource<Movie>> = MutableLiveData()

    fun fetchMovieList(genreId: String): LiveData<Resource<Movie>> {
        return if (movieList.value?.data == null) {
            viewModelScope.launch {
                movieListRepository.getMovieByCategory(genreId).collect {
                    movieList.postValue(it)
                }
            }
            movieList
        } else {
            movieList
        }
    }

    val genresList: MutableLiveData<Resource<Genres>> = MutableLiveData()

    fun fetchGenres(): LiveData<Resource<Genres>> {
        return if (genresList.value?.data == null) {
            viewModelScope.launch {
                genresRepository.fetchGenres().collect {
                    genresList.value = it
                }
            }
            genresList
        } else {
            genresList
        }
    }


}