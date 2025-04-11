package myapplication.android.musicplayerapp.ui.navigation.screen

sealed class PlaylistsScreen(val route: String) {
    data object AddTrackScreen: PlaylistsScreen(ADD_TRACK)
    data object PlaylistDetailsScreen: PlaylistsScreen(PLAYLIST_DETAILS)

    companion object{
        private const val ADD_TRACK = "add_track"
        private const val NEW_PLAYLIST = "new_playlist"
        private const val PLAYLIST_DETAILS = "playlist_details"
    }
}