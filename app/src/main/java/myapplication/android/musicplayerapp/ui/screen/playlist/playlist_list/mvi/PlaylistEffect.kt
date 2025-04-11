package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviEffect
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList

sealed interface PlaylistEffect: MviEffect {

    class OpenPlaylist(val title: String, val ids: TrackUiList): PlaylistEffect

    data object OpenAddPlaylistScreen : PlaylistEffect
}