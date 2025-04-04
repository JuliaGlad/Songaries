package myapplication.android.musicplayerapp.ui.screen.playlist.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviIntent

sealed interface PlaylistIntent: MviIntent {

    data object LoadPlaylists: PlaylistIntent

}