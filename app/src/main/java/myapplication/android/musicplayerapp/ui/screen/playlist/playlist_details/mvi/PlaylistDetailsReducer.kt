package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviReducer
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList

class PlaylistDetailsReducer: MviReducer<
        PlaylistDetailsPartialState,
        PlaylistDetailsState> {
    override fun reduce(
        prevState: PlaylistDetailsState,
        partialState: PlaylistDetailsPartialState
    ): PlaylistDetailsState =
        when(partialState){
            is PlaylistDetailsPartialState.DataLoaded -> updateDataLoaded(prevState, partialState.data)
            is PlaylistDetailsPartialState.Error -> updateError(prevState, partialState.throwable)
            PlaylistDetailsPartialState.Loading -> updateLoading(prevState)
            is PlaylistDetailsPartialState.TrackRemoved -> updateTrackRemoved(prevState, partialState.trackId)
        }

    private fun updateTrackRemoved(prevState: PlaylistDetailsState, trackId: Int) =
        prevState.copy(removedTrack = trackId)

    private fun updateDataLoaded(prevState: PlaylistDetailsState, data: TrackUiList) =
        prevState.copy(lceState = LceState.Content(data), removedTrack = -1)

    private fun updateLoading(prevState: PlaylistDetailsState) =
        prevState.copy(lceState = LceState.Loading, removedTrack = -1)

    private fun updateError(prevState: PlaylistDetailsState, throwable: Throwable) =
        prevState.copy(lceState = LceState.Error(throwable), removedTrack = -1)

}