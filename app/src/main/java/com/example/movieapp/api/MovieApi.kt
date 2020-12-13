package com.example.movieapp.api

import com.example.movieapp.api.model.detail.MovieDetail
import com.example.movieapp.api.model.genres.Genres
import com.example.movieapp.api.model.movie.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/genre/movie/list")
    suspend fun getGenreList(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ): Genres

    @GET("/3/discover/movie")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("sort_by") sort: String,
        @Query("include_video") isInclude: Boolean,
        /*@Query("page") page: Int,*/
        @Query("with_genres") genreId: String
    ): Movie

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
    ): MovieDetail

}