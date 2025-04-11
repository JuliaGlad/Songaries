package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviEffect
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiModel

sealed interface GeneralEffect: MviEffect {

    data object StartTrack: GeneralEffect

    class NavigateToAddTrack(
        val track: TrackUiModel
    ): GeneralEffect
}