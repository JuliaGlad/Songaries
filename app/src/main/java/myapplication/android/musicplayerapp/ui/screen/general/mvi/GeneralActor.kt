package myapplication.android.musicplayerapp.ui.screen.general.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import myapplication.android.musicplayerapp.domain.usecases.tracks.GetTracksUseCase
import myapplication.android.musicplayerapp.ui.main.asyncAwait
import myapplication.android.musicplayerapp.ui.main.runCatchingNonCancellation
import myapplication.android.musicplayerapp.ui.screen.mapper.toUi
import myapplication.android.musicplayerapp.ui.mvi.MviActor

class GeneralActor(
    private val getTracksUseCase: GetTracksUseCase
): MviActor<
        GeneralPartialState,
        GeneralIntent,
        GeneralState,
        GeneralEffect>() {

    override fun resolve(
        intent: GeneralIntent,
        state: GeneralState
    ): Flow<GeneralPartialState> =
        when(intent){
            GeneralIntent.LoadTracks -> loadTracks(state.offset)
        }

    private fun loadTracks(offset: Int) =
        flow {
            kotlin.runCatching {
                getTracks(offset)
            }.fold(
                onSuccess = { data ->
                    emit(GeneralPartialState.DataLoaded(data))
                },
                onFailure = { throwable ->
                   emit(GeneralPartialState.Error(throwable))
                }
            )
        }


    private suspend fun getTracks(offset: Int) =
        runCatchingNonCancellation {
            asyncAwait(
                { getTracksUseCase.invoke(offset) }
            ) { data ->
                data.toUi()
            }
        }.getOrThrow()

}