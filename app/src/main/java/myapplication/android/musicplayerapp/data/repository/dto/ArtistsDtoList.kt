package myapplication.android.musicplayerapp.data.repository.dto

class ArtistsDtoList(
    val items: List<ArtistDto>
)

class ArtistDto(
    val id: String,
    val name: String,
    val website: String,
    val image: String
)