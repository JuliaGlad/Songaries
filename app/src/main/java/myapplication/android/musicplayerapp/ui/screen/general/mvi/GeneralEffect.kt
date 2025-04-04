package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviEffect

sealed interface GeneralEffect: MviEffect {

    data object StartTrack: GeneralEffect

}