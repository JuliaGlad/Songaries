package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviIntent

sealed interface PlaylistIntent: MviIntent {

    data object LoadPlaylists: PlaylistIntent

}