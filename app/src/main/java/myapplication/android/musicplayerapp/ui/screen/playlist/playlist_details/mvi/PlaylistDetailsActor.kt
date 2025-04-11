package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import myapplication.android.musicplayerapp.domain.usecases.playlist.DeleteTrackFromPlaylistUseCase
import myapplication.android.musicplayerapp.domain.usecases.tracks.GetTracksByIdsUseCase
import myapplication.android.musicplayerapp.ui.main.asyncAwait
import myapplication.android.musicplayerapp.ui.main.runCatchingNonCancellation
import myapplication.android.musicplayerapp.ui.mvi.MviActor
import myapplication.android.musicplayerapp.ui.screen.mapper.toUi

class PlaylistDetailsActor(
    private val getTracksByIdsUseCase: GetTracksByIdsUseCase,
    private val deleteTracksFromPlaylistUseCase: DeleteTrackFromPlaylistUseCase
): MviActor<
        PlaylistDetailsPartialState,
        PlaylistDetailsIntent,
        PlaylistDetailsState,
        PlaylistDetailsEffect>() {
    override fun resolve(
        intent: PlaylistDetailsIntent,
        state: PlaylistDetailsState
    ): Flow<PlaylistDetailsPartialState> =
        when(intent){
            is PlaylistDetailsIntent.GetTracks -> loadTracksByIds(intent.ids)
            is PlaylistDetailsIntent.RemoveTrack -> removeTrackById(
                intent.title,
                intent.id
            )
        }

    private fun loadTracksByIds(ids: List<Int>) =
        flow {
            kotlin.runCatching {
                getTracksByIds(ids)
            }.fold(
                onSuccess = { data ->
                    emit(PlaylistDetailsPartialState.DataLoaded(data))
                },
                onFailure = { throwable ->
                    emit(PlaylistDetailsPartialState.Error(throwable))
                }
            )
        }

    private fun removeTrackById(title: String, id: Int) =
        flow {
            kotlin.runCatching {
                deleteTracksFromPlaylistUseCase.invoke(title, id)
            }.fold(
                onSuccess = { emit(PlaylistDetailsPartialState.TrackRemoved(id)) },
                onFailure = { throwable ->
                    emit(PlaylistDetailsPartialState.Error(throwable))
                }
            )
        }

    private suspend fun getTracksByIds(ids: List<Int>) =
        runCatchingNonCancellation {
            asyncAwait(
                { getTracksByIdsUseCase.invoke(ids) }
            ){ data ->
                data.toUi()
            }
        }.getOrThrow()

}