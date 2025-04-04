package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviIntent

sealed interface GeneralIntent: MviIntent {

    data object LoadTracks : GeneralIntent

}