package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviEffect

sealed interface PlaylistDetailsEffect: MviEffect {

    data object NavigateBack: PlaylistDetailsEffect

    class ShowDeleteDialog(val title: String, val id: Int): PlaylistDetailsEffect

    class StartTrack(val uri: String): PlaylistDetailsEffect
}