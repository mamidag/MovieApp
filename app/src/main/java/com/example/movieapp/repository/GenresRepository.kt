package com.example.movieapp.repository

import com.example.movieapp.api.MovieApi
import com.example.movieapp.api.model.genres.Genres
import com.example.movieapp.api.param.lang
import com.example.movieapp.data.Resource
import com.example.movieapp.util.keys.ApiKeys
import com.github.ajalt.timberkt.e
import com.github.ajalt.timberkt.i
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

import javax.inject.Inject


@ExperimentalCoroutinesApi
class GenresRepository @Inject constructor(
    private val api: MovieApi
) {

    fun fetchGenres(): Flow<Resource<Genres>> {
        return flow {
            val genreList = api.getGenreList(
                apiKey = ApiKeys.apiKeyV3,
                lang = lang
            )
            emit(Resource.success(genreList))
        }.retryWhen { cause, attempt ->
            i { "attempt count -> $attempt" }
            e { "cause -> $cause" }
            (cause is Exception).also { if (it) delay(10_000) }
        }.catch{
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(Dispatchers.IO)


    }


}
