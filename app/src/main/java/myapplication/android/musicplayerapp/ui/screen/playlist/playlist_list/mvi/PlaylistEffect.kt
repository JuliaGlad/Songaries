package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviEffect

sealed interface PlaylistEffect: MviEffect {

    class OpenPlaylist(val title: String): PlaylistEffect

    data object OpenAddPlaylistScreen: PlaylistEffect
}