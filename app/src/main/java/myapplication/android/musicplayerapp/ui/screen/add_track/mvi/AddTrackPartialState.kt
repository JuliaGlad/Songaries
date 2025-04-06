package myapplication.android.musicplayerapp.ui.screen.add_track.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviPartialState
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUiList

sealed interface AddTrackPartialState: MviPartialState {

    data object Loading: AddTrackPartialState

    class DataLoaded(val data: PlaylistUiList): AddTrackPartialState

    data object TrackAdded: AddTrackPartialState

    class Error(val throwable: Throwable): AddTrackPartialState
}