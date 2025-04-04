package myapplication.android.musicplayerapp.domain.models

class PlaylistsDomainList(
    val items: List<PlaylistDomain>
)

class PlaylistDomain(
    val title: String,
    val image: String,
    val description: String,
    val tracks: List<Int>
)