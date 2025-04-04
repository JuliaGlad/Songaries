package myapplication.android.musicplayerapp.ui.screen.general

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
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralEffect
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralIntent
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralLocalDI
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralPartialState
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralState
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralStore
import javax.inject.Inject

@HiltViewModel
class GeneralScreenViewModel  @Inject constructor(
    localDI: GeneralLocalDI
): MviBaseViewModel<
        GeneralPartialState,
        GeneralIntent,
        GeneralState,
        GeneralEffect>() {

    override val store: GeneralStore = GeneralStore(localDI.actor, localDI.reducer)

    private val _effects: MutableSharedFlow<GeneralEffect> = MutableSharedFlow()
    val effects: SharedFlow<GeneralEffect> = _effects.asSharedFlow()

    private val _uiState: MutableStateFlow<GeneralState> = MutableStateFlow(value = GeneralState(LceState.Loading))
    val uiState: StateFlow<GeneralState> = _uiState.asStateFlow()

    init {
        startCollecting()
        store.sendIntent(GeneralIntent.LoadTracks)
    }

    override fun resolveEffect(effect: GeneralEffect) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    override fun resolveState(state: GeneralState) {
        viewModelScope.launch {
            _uiState.emit(state)
        }
    }

    fun sendEffect(effect: GeneralEffect){
        store.sendEffect(effect)
    }

    fun sendIntent(intent: GeneralIntent){
        store.sendIntent(intent)
    }

}