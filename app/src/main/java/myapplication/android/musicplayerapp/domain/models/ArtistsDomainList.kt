package myapplication.android.musicplayerapp.domain.models

class ArtistsDomainList(
    val items: List<ArtistDomain>
)
class ArtistDomain(
    val id: String,
    val name: String,
    val website: String,
    val image: String
)