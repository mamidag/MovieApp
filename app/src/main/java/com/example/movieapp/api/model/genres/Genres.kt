package com.example.movieapp.api.model.genres

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable
data class Genres(
    @SerialName("genres")
    var list : List<Genre>
)

@Serializable
data class Genre(
    @SerialName("id")
    var id: Int? = 0,
    @SerialName("name")
    var name: String?=""
)

/*
data class Genre (
    val id: Int,
    val name: String
)
*/

/*
@Serializable(with = Genres::class)
data class Genre (
    val values: List<Genres>
)

object PolygonSerializer : KSerializer<Genre> {
    private val serializer = ArrayListSerializer(Double.serializer())

    override val descriptor: SerialDescriptor = StringDescriptor.withName("Genres")

    override fun serialize(encoder: Encoder, obj: Polygon) {
        encoder.encodeInt(serializer, obj.values)
    }

    override fun deserialize(decoder: Decoder): Polygon {
        return Polygon(decoder.decode(serializer))
    }
}*/
