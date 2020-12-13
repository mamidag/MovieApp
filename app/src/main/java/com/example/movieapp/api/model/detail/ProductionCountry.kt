package com.example.movieapp.api.model.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class ProductionCountry(
    @SerialName("iso_3166_1")
    var iso31661: String,
    @SerialName("name")
    var name: String
):Parcelable