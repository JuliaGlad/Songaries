package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviEffect

sealed interface AddTrackEffect: MviEffect {

    data object NavigateBack: AddTrackEffect

    data object OpenAddPlaylistScreen: AddTrackEffect
}