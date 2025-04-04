package myapplication.android.musicplayerapp.domain.models

class AlbumsDomainList(
    val items: List<AlbumDomain>
)

class AlbumDomain(
    val id: String,
    val title: String,
    val releaseDate: String,
    val image: String
)