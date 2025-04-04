package myapplication.android.musicplayerapp.ui.screen.playlist.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviStore
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralActor
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralEffect
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralIntent
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralPartialState
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralReducer
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralState

class PlaylistStore(
    actor: PlaylistActor,
    reducer: PlaylistReducer
) : MviStore<
        PlaylistPartialState,
        PlaylistIntent,
        PlaylistState,
        PlaylistEffect>(reducer, actor) {
    override fun initialStateCreator(): PlaylistState =
        PlaylistState(lceState = LceState.Loading)

}