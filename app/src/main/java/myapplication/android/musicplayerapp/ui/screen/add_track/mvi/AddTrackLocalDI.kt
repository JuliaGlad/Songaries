package myapplication.android.musicplayerapp.ui.screen.add_track.mvi

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.domain.usecases.playlist.AddTrackToPlaylistUseCase
import myapplication.android.musicplayerapp.domain.usecases.playlist.GetPlaylistsUseCase
import javax.inject.Inject

class AddTrackLocalDI @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {

    private val getPlaylistsUseCase by lazy { GetPlaylistsUseCase(playlistRepository) }

    private val addTrackToPlaylistUseCase by lazy { AddTrackToPlaylistUseCase(playlistRepository) }

    val actor by lazy { AddTrackActor(getPlaylistsUseCase, addTrackToPlaylistUseCase) }

    val reducer by lazy { AddTrackReducer() }

}