package myapplication.android.musicplayerapp.ui.screen.general.mvi

import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.domain.usecases.tracks.GetTracksUseCase
import javax.inject.Inject

class GeneralLocalDI @Inject constructor(
    private val repository: TracksRepository
) {
    private val getTracksUseCase by lazy { GetTracksUseCase(repository) }

    val actor by lazy { GeneralActor(getTracksUseCase) }

    val reducer by lazy { GeneralReducer() }
}