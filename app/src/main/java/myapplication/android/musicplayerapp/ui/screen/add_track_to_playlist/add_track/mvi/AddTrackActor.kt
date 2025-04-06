package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import myapplication.android.musicplayerapp.domain.usecases.playlist.AddTrackToPlaylistUseCase
import myapplication.android.musicplayerapp.domain.usecases.playlist.GetPlaylistsUseCase
import myapplication.android.musicplayerapp.ui.main.asyncAwait
import myapplication.android.musicplayerapp.ui.main.runCatchingNonCancellation
import myapplication.android.musicplayerapp.ui.mvi.MviActor
import myapplication.android.musicplayerapp.ui.screen.playlist.mapper.toUi

class AddTrackActor(
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    private val addTrackToPlaylistUseCase: AddTrackToPlaylistUseCase
): MviActor<
        AddTrackPartialState,
        AddTrackIntent,
        AddTrackState,
        AddTrackEffect>() {
    override fun resolve(
        intent: AddTrackIntent,
        state: AddTrackState
    ): Flow<AddTrackPartialState> =
        when(intent){
            is AddTrackIntent.AddTrack -> addTrack(
                intent.playlistTitle,
                intent.id.toInt()
            )
            AddTrackIntent.GetPlaylists -> loadPlaylists()
        }

    private fun loadPlaylists() =
        flow {
            kotlin.runCatching {
                getPlaylist()
            }.fold(
                onSuccess = { data ->
                    emit(AddTrackPartialState.DataLoaded(data))
                },
                onFailure = { throwable ->
                    emit(AddTrackPartialState.Error(throwable))
                }
            )
        }

    private suspend fun getPlaylist() =
        runCatchingNonCancellation {
            asyncAwait(
                { getPlaylistsUseCase.invoke() }
            ) { data ->
                data.toUi()
            }
        }.getOrThrow()

    private fun addTrack(playlist: String, trackId: Int) =
        flow {
            kotlin.runCatching {
                addTrackToPlaylistUseCase.invoke(playlist, trackId)
            }.fold(
                onSuccess = { emit(AddTrackPartialState.TrackAdded) },
                onFailure = { throwable ->
                    emit(AddTrackPartialState.Error(throwable))
                }
            )
        }
}