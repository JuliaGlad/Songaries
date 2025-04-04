package myapplication.android.musicplayerapp.ui.screen.model

class TrackUiList(
    val tracks: List<TrackUiModel>
)

class TrackUiModel(
    val trackId: String,
    val title: String,
    val image: String,
    val audioUri: String,
    val artistId: String,
    val artist: String
)