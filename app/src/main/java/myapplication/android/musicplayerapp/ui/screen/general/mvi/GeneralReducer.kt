package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviReducer

class GeneralReducer: MviReducer<
        GeneralPartialState,
        GeneralState> {
    override fun reduce(
        prevState: GeneralState,
        partialState: GeneralPartialState
    ): GeneralState =
        when(partialState){
            is GeneralPartialState.DataLoaded -> updateDataLoaded(prevState, partialState.data)
            is GeneralPartialState.Error -> updateError(prevState, partialState.throwable)
            GeneralPartialState.Loading -> updateLoading(prevState)
        }

    private fun updateLoading(prevState: GeneralState) =
        prevState.copy(lceState = LceState.Loading)

    private fun updateError(prevState: GeneralState, throwable: Throwable) =
        prevState.copy(lceState = LceState.Error(throwable))

    private fun updateDataLoaded(prevState: GeneralState, tracks: TrackUiList) =
        prevState.copy(
            lceState = LceState.Content(tracks),
            offset = prevState.offset + tracks.tracks.size
        )
}