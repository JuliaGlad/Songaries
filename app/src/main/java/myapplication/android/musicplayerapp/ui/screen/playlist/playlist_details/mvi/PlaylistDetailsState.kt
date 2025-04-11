package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviState
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList

data class PlaylistDetailsState(val lceState: LceState<TrackUiList>, val removedTrack: Int = -1): MviState