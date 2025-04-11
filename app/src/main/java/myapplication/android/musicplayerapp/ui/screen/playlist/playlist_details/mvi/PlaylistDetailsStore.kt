package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviStore

class PlaylistDetailsStore(
    actor: PlaylistDetailsActor,
    reducer: PlaylistDetailsReducer
): MviStore<
        PlaylistDetailsPartialState,
        PlaylistDetailsIntent,
        PlaylistDetailsState,
        PlaylistDetailsEffect>(reducer, actor) {
    override fun initialStateCreator(): PlaylistDetailsState =  PlaylistDetailsState(LceState.Loading)
}