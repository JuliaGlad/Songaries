package myapplication.android.musicplayerapp.ui.screen.playlist.mvi

import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviState
import myapplication.android.musicplayerapp.ui.screen.playlist.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.screen.playlist.model.PlaylistUiList

data class PlaylistState(val lceState: LceState<PlaylistUiList>): MviState
