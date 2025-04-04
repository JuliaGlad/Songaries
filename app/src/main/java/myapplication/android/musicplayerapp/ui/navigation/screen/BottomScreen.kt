package myapplication.android.musicplayerapp.ui.navigation.screen

sealed class BottomScreen(val route: String) {
    data object GeneralScreen: BottomScreen(MUSIC)
    data object ArtistsScreen: BottomScreen(ARTISTS)
    data object PlaylistsScreen: BottomScreen(PLAYLISTS)

    companion object{
        private const val MUSIC = "music"
        private const val ARTISTS = "artists"
        private const val PLAYLISTS = "playlists"
    }
}