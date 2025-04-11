package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import myapplication.android.musicplayerapp.domain.usecases.playlist.DeleteTrackFromPlaylistUseCase
import myapplication.android.musicplayerapp.ui.main.runCatchingNonCancellation
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(
    private val deleteTrackFromPlaylistUseCase: DeleteTrackFromPlaylistUseCase
): ViewModel() {

    private val _removed: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)
    val removed = _removed.asSharedFlow()

    private val _onError: MutableSharedFlow<Throwable?> = MutableSharedFlow(replay = 1)
    val onError = _onError.asSharedFlow()

    init {
        _removed.tryEmit(-1)
    }

    fun removeTrackFromPlaylist(
        title: String,
        id: String
    ) {
        kotlin.runCatching {
            removeTrackPlaylist(title, id.toInt())
        }.fold(
            onSuccess = {
                _removed.tryEmit(id.toInt())
            },
            onFailure = { _onError.tryEmit(it) }
        )
    }

    private fun removeTrackPlaylist(
        title: String,
        id: Int,
    ) = runCatchingNonCancellation {
        viewModelScope.launch {
           deleteTrackFromPlaylistUseCase.invoke(
                title, id
            )
        }
    }.getOrThrow()

//    override val store: PlaylistDetailsStore = PlaylistDetailsStore(localDI.actor, localDI.reducer)
//
//    private val _effects: MutableSharedFlow<PlaylistDetailsEffect> = MutableSharedFlow()
//    val effects: SharedFlow<PlaylistDetailsEffect> = _effects.asSharedFlow()
//
//    private val _uiState: MutableStateFlow<PlaylistDetailsState> = MutableStateFlow(value = PlaylistDetailsState(LceState.Loading))
//    val uiState: StateFlow<PlaylistDetailsState> = _uiState.asStateFlow()
//
//    init {
//        startCollecting()
//    }
//
//    override fun resolveEffect(effect: PlaylistDetailsEffect) {
//        viewModelScope.launch {
//            _effects.emit(effect)
//        }
//    }
//
//    override fun resolveState(state: PlaylistDetailsState) {
//        viewModelScope.launch {
//            _uiState.emit(state)
//        }
//    }
//
//    fun sendIntent(intent: PlaylistDetailsIntent){
//        store.sendIntent(intent)
//    }
//
//    fun sendEffect(effect: PlaylistDetailsEffect){
//        store.sendEffect(effect)
//    }

}