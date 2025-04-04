package myapplication.android.musicplayerapp.ui.navigation.screen

sealed class PlaylistsScreen(val route: String) {
    data object NewPlaylistScreen: PlaylistsScreen(NEW_PLAYLIST)
    data object ArtistsScreen: PlaylistsScreen(PLAYLIST_DETAILS)

    companion object{
        private const val NEW_PLAYLIST = "new_playlist"
        private const val PLAYLIST_DETAILS = "playlist_details"
    }
}