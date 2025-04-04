package myapplication.android.musicplayerapp.data.api.models

import kotlinx.serialization.SerialName

class ArtistsList(
    @SerialName("results") val items: List<Artist>
)

class Artist(
    val id: String,
    val name: String,
    val website: String,
    val image: String
)