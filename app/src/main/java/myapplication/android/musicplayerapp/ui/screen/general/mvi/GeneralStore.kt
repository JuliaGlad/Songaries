package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviStore
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralActor
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralEffect
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralIntent
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralPartialState
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralReducer
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralState

class GeneralStore(
    actor: GeneralActor,
    reducer: GeneralReducer
) : MviStore<
        GeneralPartialState,
        GeneralIntent,
        GeneralState,
        GeneralEffect>(reducer, actor) {
    override fun initialStateCreator(): GeneralState =
        GeneralState(lceState = LceState.Loading)

}