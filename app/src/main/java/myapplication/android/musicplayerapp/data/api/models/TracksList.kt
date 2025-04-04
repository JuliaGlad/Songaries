package myapplication.android.musicplayerapp.data.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TracksList(
    @SerialName("results") val items: List<Track>
)

@Serializable
class Track(
    val id: String,
    @SerialName("name") val title: String,
    val image: String,
    val audio: String,
    @SerialName("artist_id") val artistId: String,
    @SerialName("artist_name") val artist: String
)