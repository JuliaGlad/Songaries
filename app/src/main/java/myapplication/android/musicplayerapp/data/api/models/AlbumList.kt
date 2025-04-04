package myapplication.android.musicplayerapp.data.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AlbumList(
    @SerialName("results") val artists: ArtistsAlbumList
)

@Serializable
class ArtistsAlbumList(
    val albums: List<Album>
)

@Serializable
class Album(
    val id: String,
    @SerialName("name") val title: String,
    @SerialName("releasedate") val releaseDate: String,
    val image: String
)