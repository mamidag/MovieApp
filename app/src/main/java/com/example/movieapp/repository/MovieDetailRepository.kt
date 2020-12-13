package com.example.movieapp.repository

import com.example.movieapp.api.MovieApi
import com.example.movieapp.api.model.detail.MovieDetail
import com.example.movieapp.api.param.lang
import com.example.movieapp.data.Resource
import com.example.movieapp.util.keys.ApiKeys
import com.github.ajalt.timberkt.e
import com.github.ajalt.timberkt.i
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val api : MovieApi
){

    fun fetchMovieDetail(movieId:Int) : Flow<Resource<MovieDetail>>{
        return flow {
            emit(Resource.loading(null))
            var movieDetail = api.getMovie(
                movie_id = movieId,
                apiKey = ApiKeys.apiKeyV3,
                lang =  lang
            )
            emit(Resource.success(movieDetail))
        }.retryWhen { cause, attempt ->
            i { "attempt count -> $attempt" }
            e { "cause -> $cause" }
            (cause is Exception).also { if (it) delay(10_000) }
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(Dispatchers.IO)

    }


}