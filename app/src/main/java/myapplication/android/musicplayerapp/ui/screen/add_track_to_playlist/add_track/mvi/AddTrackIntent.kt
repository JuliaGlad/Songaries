package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviIntent
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiModel

sealed interface AddTrackIntent: MviIntent {

    data object GetPlaylists: AddTrackIntent

    class AddTrack(
        val playlistTitle: String,
        val track: TrackUiModel
    ): AddTrackIntent
}