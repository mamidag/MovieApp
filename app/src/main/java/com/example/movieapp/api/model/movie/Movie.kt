package com.example.movieapp.api.model.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Movie(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Results>,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("total_results")
    val total_results: Int
) : Parcelable
