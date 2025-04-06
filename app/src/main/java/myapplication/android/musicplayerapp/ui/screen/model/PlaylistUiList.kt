package myapplication.android.musicplayerapp.ui.screen.model

class PlaylistUiList(
    val playlists: List<PlaylistUi>
) 
class PlaylistUi(
    val title: String,
    val image: String,
    val description: String,
    val tracks: List<Int>
)