package com.example.movieapp.api.model.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Genre(
    @SerialName("id")
    var id: Int,
    @SerialName("name")
    var name: String
):Parcelable