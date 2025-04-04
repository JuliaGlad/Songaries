package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviState

data class GeneralState(
    val lceState: LceState<TrackUiList>,
    val offset: Int = 0
): MviState
