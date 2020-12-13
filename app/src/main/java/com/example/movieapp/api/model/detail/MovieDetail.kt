package com.example.movieapp.api.model.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MovieDetail(
    @SerialName("adult")
    var adult: Boolean,
    @SerialName("backdrop_path")
    var backdropPath: String,
    @SerialName("belongs_to_collection")
    var belongsToCollection: String?="",
    @SerialName("budget")
    var budget: Int,
    @SerialName("genres")
    var genres: List<Genre>,
    @SerialName("homepage")
    var homepage: String,
    @SerialName("id")
    var id: Int,
    @SerialName("imdb_id")
    var imdbId: String,
    @SerialName("original_language")
    var originalLanguage: String,
    @SerialName("original_title")
    var originalTitle: String,
    @SerialName("overview")
    var overview: String,
    @SerialName("popularity")
    var popularity: Double,
    @SerialName("poster_path")
    var posterPath: String,
    @SerialName("production_companies")
    var productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries")
    var productionCountries: List<ProductionCountry>,
    @SerialName("release_date")
    var releaseDate: String,
    @SerialName("revenue")
    var revenue: Int,
    @SerialName("runtime")
    var runtime: Int,
    @SerialName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>,
    @SerialName("status")
    var status: String,
    @SerialName("tagline")
    var tagline: String,
    @SerialName("title")
    var title: String,
    @SerialName("video")
    var video: Boolean,
    @SerialName("vote_average")
    var voteAverage: Double,
    @SerialName("vote_count")
    var voteCount: Int
):Parcelable