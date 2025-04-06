package myapplication.android.musicplayerapp.ui.screen.playlist.new_playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import myapplication.android.musicplayerapp.domain.usecases.playlist.AddPlaylistUseCase
import myapplication.android.musicplayerapp.ui.main.runCatchingNonCancellation
import javax.inject.Inject

@HiltViewModel
class NewPlaylistViewModel @Inject constructor(
    private val addPlaylistUseCase: AddPlaylistUseCase
) : ViewModel() {

    private val _isAdded: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1)
    val isAdded = _isAdded.asSharedFlow()

    private val _onError: MutableSharedFlow<Throwable?> = MutableSharedFlow(replay = 1)
    val onError = _onError.asSharedFlow()

    init {
        _isAdded.tryEmit(false)
    }

    fun addNewPlaylist(
        title: String,
        description: String = "",
        image: String = ""
    ) {
        kotlin.runCatching {
            addPlaylist(title, description, image)
        }.fold(
            onSuccess = {
                _isAdded.tryEmit(true)
            },
            onFailure = { _onError.tryEmit(it) }
        )
    }

    private fun addPlaylist(
        title: String,
        description: String,
        image: String
    ) = runCatchingNonCancellation {
        viewModelScope.launch {
            addPlaylistUseCase.invoke(
                title = title,
                image = image,
                description = description
            )
        }
    }.getOrThrow()

}