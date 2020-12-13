package com.example.movieapp.listener

import com.example.movieapp.api.model.genres.Genre
import com.example.movieapp.api.model.movie.Results

interface IListener<T> {
    fun onClick(item: Results)
}
