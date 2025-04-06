package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.domain.usecases.playlist.AddPlaylistUseCase
import myapplication.android.musicplayerapp.domain.usecases.playlist.GetPlaylistsUseCase
import myapplication.android.musicplayerapp.domain.usecases.tracks.GetTracksUseCase
import javax.inject.Inject

class PlaylistLocalDI @Inject constructor(
    private val repository: PlaylistRepository
) {
    private val getPlaylistUseCase by lazy { GetPlaylistsUseCase(repository) }

    val actor by lazy { PlaylistActor(getPlaylistUseCase) }

    val reducer by lazy { PlaylistReducer() }
}