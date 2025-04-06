package myapplication.android.musicplayerapp.ui.screen.add_track

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.mvi.MviBaseViewModel
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackEffect
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackIntent
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackLocalDI
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackPartialState
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackState
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackStore
import javax.inject.Inject

@HiltViewModel
class AddTrackViewModel @Inject constructor(
    localDI: AddTrackLocalDI
): MviBaseViewModel<
        AddTrackPartialState,
        AddTrackIntent,
        AddTrackState,
        AddTrackEffect>() {

    override val store = AddTrackStore(localDI.actor, localDI.reducer)

    private val _effects: MutableSharedFlow<AddTrackEffect> = MutableSharedFlow()
    val effects: SharedFlow<AddTrackEffect> = _effects.asSharedFlow()

    private val _uiState: MutableStateFlow<AddTrackState> = MutableStateFlow(value = AddTrackState(
        LceState.Loading)
    )
    val uiState: StateFlow<AddTrackState> = _uiState.asStateFlow()

    init {
        startCollecting()
        store.sendIntent(AddTrackIntent.GetPlaylists)
    }

    override fun resolveEffect(effect: AddTrackEffect) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    override fun resolveState(state: AddTrackState) {
        viewModelScope.launch {
            _uiState.emit(state)
        }
    }

    fun sendEffect(effect: AddTrackEffect){
        store.sendEffect(effect)
    }

    fun sendIntent(intent: AddTrackIntent){
        store.sendIntent(intent)
    }

}