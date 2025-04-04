package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.mvi.MviPartialState

sealed interface GeneralPartialState: MviPartialState {

    class Error(val throwable: Throwable): GeneralPartialState

    class DataLoaded(val data: TrackUiList): GeneralPartialState

    data object Loading: GeneralPartialState

}