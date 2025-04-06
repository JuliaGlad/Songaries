package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import myapplication.android.musicplayerapp.domain.usecases.playlist.GetPlaylistsUseCase
import myapplication.android.musicplayerapp.ui.main.asyncAwait
import myapplication.android.musicplayerapp.ui.main.runCatchingNonCancellation
import myapplication.android.musicplayerapp.ui.mvi.MviActor
import myapplication.android.musicplayerapp.ui.screen.playlist.mapper.toUi

class PlaylistActor(
    private val getPlaylistsUseCase: GetPlaylistsUseCase
) : MviActor<
        PlaylistPartialState,
        PlaylistIntent,
        PlaylistState,
        PlaylistEffect>() {

    override fun resolve(
        intent: PlaylistIntent,
        state: PlaylistState
    ): Flow<PlaylistPartialState> =
        when (intent) {
            PlaylistIntent.LoadPlaylists -> loadPlaylists()
        }

    private fun loadPlaylists() =
        flow {
            kotlin.runCatching {
                getPlaylist()
            }.fold(
                onSuccess = { data ->
                    emit(PlaylistPartialState.DataLoaded(data))
                },
                onFailure = { throwable ->
                    emit(PlaylistPartialState.Error(throwable))
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


}