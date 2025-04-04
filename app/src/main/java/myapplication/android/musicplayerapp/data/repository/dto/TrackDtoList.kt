package myapplication.android.musicplayerapp.data.repository.dto

class TrackDtoList(
    val items: List<TrackDto>
)

class TrackDto(
    val id: String,
    val title: String,
    val image: String,
    val audioUri: String,
    val artistId: String,
    val artist: String
)