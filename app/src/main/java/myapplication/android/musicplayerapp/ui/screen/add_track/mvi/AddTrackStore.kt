package myapplication.android.musicplayerapp.ui.screen.add_track.mvi

import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviStore

class AddTrackStore(
    actor: AddTrackActor,
    reducer: AddTrackReducer
): MviStore<
        AddTrackPartialState,
        AddTrackIntent,
        AddTrackState,
        AddTrackEffect>(reducer, actor) {
    override fun initialStateCreator(): AddTrackState = AddTrackState(LceState.Loading)
}