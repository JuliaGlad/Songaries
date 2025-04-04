package myapplication.android.musicplayerapp.domain.models

class TracksDomainList(
    val items: List<TrackDomain>
)

class TrackDomain(
    val id: String,
    val title: String,
    val image: String,
    val audioUri: String,
    val artistId: String,
    val artist: String
)