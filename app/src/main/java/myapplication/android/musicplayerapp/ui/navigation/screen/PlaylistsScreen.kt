package myapplication.android.musicplayerapp.ui.navigation.screen

sealed class PlaylistsScreen(val route: String) {
    data object NewPlaylistScreen: PlaylistsScreen(NEW_PLAYLIST)
    data object ArtistsScreen: PlaylistsScreen(PLAYLIST_DETAILS)
    data object AddTrackScreen: PlaylistsScreen(ADD_TRACK)

    companion object{
        private const val ADD_TRACK = "add_track"
        private const val NEW_PLAYLIST = "new_playlist"
        private const val PLAYLIST_DETAILS = "playlist_details"
    }
}