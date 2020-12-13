package com.example.movieapp.repository

import com.example.movieapp.api.MovieApi
import com.example.movieapp.api.model.movie.Movie
import com.example.movieapp.api.param.include_video
import com.example.movieapp.api.param.lang
import com.example.movieapp.api.param.sort_by
import com.example.movieapp.data.Resource
import com.example.movieapp.util.keys.ApiKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val api: MovieApi
) {

    suspend fun getMovieByCategory(genreId: String): Flow<Resource<Movie>> {
        val movie = flow {
            emit(Resource.loading(null))
            val items = api.getMoviesList(
                apiKey = ApiKeys.apiKeyV3,
                lang = lang,
                sort = sort_by,
                isInclude = include_video,
                genreId = genreId
            )
            emit(Resource.success(items))
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(Dispatchers.IO)
        return movie
    }
}