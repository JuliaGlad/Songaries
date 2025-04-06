package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviReducer
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUiList

class PlaylistReducer: MviReducer<
        PlaylistPartialState,
        PlaylistState> {
    override fun reduce(
        prevState: PlaylistState,
        partialState: PlaylistPartialState
    ): PlaylistState =
        when(partialState){
            is PlaylistPartialState.DataLoaded -> updateDataLoaded(prevState, partialState.data)
            is PlaylistPartialState.Error -> updateError(prevState, partialState.throwable)
            PlaylistPartialState.Loading -> updateLoading(prevState)
        }

    private fun updateLoading(prevState: PlaylistState) =
        prevState.copy(lceState = LceState.Loading)

    private fun updateError(prevState: PlaylistState, throwable: Throwable) =
        prevState.copy(lceState = LceState.Error(throwable))

    private fun updateDataLoaded(prevState: PlaylistState, tracks: PlaylistUiList) =
        prevState.copy(
            lceState = LceState.Content(tracks)
        )
}