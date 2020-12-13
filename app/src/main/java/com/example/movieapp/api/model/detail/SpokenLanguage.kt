package com.example.movieapp.api.model.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class SpokenLanguage(
    @SerialName("english_name")
    var englishName: String,
    @SerialName("iso_639_1")
    var iso6391: String,
    @SerialName("name")
    var name: String
):Parcelable