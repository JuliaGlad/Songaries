package myapplication.android.musicplayerapp.data.repository.dto

class PlaylistDtoList(
    val items: List<PlaylistDto>
)

class PlaylistDto(
    val title: String,
    val image: String,
    val description: String,
    val tracks: List<Int>
)