package myapplication.android.musicplayerapp.ui.mvi

interface MviReducer<PartialState: MviPartialState, State: MviState> {

    fun reduce(prevState: State, partialState: PartialState): State
}