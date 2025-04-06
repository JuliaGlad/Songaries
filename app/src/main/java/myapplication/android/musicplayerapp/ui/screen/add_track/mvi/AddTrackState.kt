package myapplication.android.musicplayerapp.ui.screen.add_track.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviState
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUiList

data class AddTrackState(val state: LceState<PlaylistUiList>, val isAdded: Boolean = false): MviState