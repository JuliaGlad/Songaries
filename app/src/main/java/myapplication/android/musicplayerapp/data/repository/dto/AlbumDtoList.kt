package myapplication.android.musicplayerapp.data.repository.dto

class AlbumDtoList(
    val items: List<AlbumDto>
)
class AlbumDto(
    val id: String,
    val title: String,
    val releaseDate: String,
    val image: String
)