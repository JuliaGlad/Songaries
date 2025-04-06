package myapplication.android.musicplayerapp.ui.screen.add_track.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviIntent

sealed interface AddTrackIntent: MviIntent {

    data object GetPlaylists: AddTrackIntent

    class AddTrack(
        val playlistTitle: String,
        val id: String
    ): AddTrackIntent
}