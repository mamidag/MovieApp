package com.example.movieapp.api.model.detail


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ProductionCompany(
    @SerialName("id")
    var id: Int,
    @SerialName("logo_path")
    var logoPath: String?="",
    @SerialName("name")
    var name: String,
    @SerialName("origin_country")
    var originCountry: String
):Parcelable