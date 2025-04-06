package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviReducer
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUiList

class AddTrackReducer : MviReducer<
        AddTrackPartialState,
        AddTrackState> {
    override fun reduce(
        prevState: AddTrackState,
        partialState: AddTrackPartialState
    ): AddTrackState =
        when(partialState){
            is AddTrackPartialState.DataLoaded -> updateDataLoaded(prevState, partialState.data)
            is AddTrackPartialState.Error -> updateError(prevState, partialState.throwable)
            AddTrackPartialState.Loading -> updateLoading(prevState)
            AddTrackPartialState.TrackAdded -> updateTrackAdded(prevState)
        }

    private fun updateTrackAdded(prevState: AddTrackState) =
        prevState.copy(isAdded = true)

    private fun updateDataLoaded(prevState: AddTrackState, playlistUi: PlaylistUiList) =
        prevState.copy(state = LceState.Content(playlistUi))

    private fun updateError(prevState: AddTrackState, throwable: Throwable) =
        prevState.copy(state = LceState.Error(throwable))

    private fun updateLoading(prevState: AddTrackState) =
        prevState.copy(state = LceState.Loading)
}