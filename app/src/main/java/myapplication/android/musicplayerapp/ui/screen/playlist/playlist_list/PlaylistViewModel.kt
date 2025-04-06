package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list

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
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistEffect
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistIntent
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistLocalDI
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistPartialState
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistState
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistStore
import javax.inject.Inject


@HiltViewModel
class PlaylistViewModel  @Inject constructor(
    localDI: PlaylistLocalDI
): MviBaseViewModel<
        PlaylistPartialState,
        PlaylistIntent,
        PlaylistState,
        PlaylistEffect>() {

    override val store: PlaylistStore = PlaylistStore(localDI.actor, localDI.reducer)

    private val _effects: MutableSharedFlow<PlaylistEffect> = MutableSharedFlow()
    val effects: SharedFlow<PlaylistEffect> = _effects.asSharedFlow()

    private val _uiState: MutableStateFlow<PlaylistState> = MutableStateFlow(value = PlaylistState(LceState.Loading))
    val uiState: StateFlow<PlaylistState> = _uiState.asStateFlow()

    init {
        startCollecting()
        store.sendIntent(PlaylistIntent.LoadPlaylists)
    }

    override fun resolveEffect(effect: PlaylistEffect) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    override fun resolveState(state: PlaylistState) {
        viewModelScope.launch {
            _uiState.emit(state)
        }
    }

    fun sendEffect(effect: PlaylistEffect){
        store.sendEffect(effect)
    }

}