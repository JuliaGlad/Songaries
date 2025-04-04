package myapplication.android.musicplayerapp.ui.screen.playlist.mvi

import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.mvi.MviPartialState
import myapplication.android.musicplayerapp.ui.screen.playlist.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.screen.playlist.model.PlaylistUiList

sealed interface PlaylistPartialState: MviPartialState {

    class Error(val throwable: Throwable): PlaylistPartialState

    class DataLoaded(val data: PlaylistUiList): PlaylistPartialState

    data object Loading: PlaylistPartialState

}