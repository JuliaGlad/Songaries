package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviPartialState
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList

sealed interface PlaylistDetailsPartialState: MviPartialState {

    data object Loading: PlaylistDetailsPartialState
    
    class DataLoaded(val data: TrackUiList): PlaylistDetailsPartialState

    class TrackRemoved(val trackId: Int): PlaylistDetailsPartialState

    class Error(val throwable: Throwable): PlaylistDetailsPartialState

}