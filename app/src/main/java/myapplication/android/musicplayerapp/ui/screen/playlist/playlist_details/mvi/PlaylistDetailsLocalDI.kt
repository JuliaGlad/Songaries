package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.domain.usecases.playlist.DeleteTrackFromPlaylistUseCase
import myapplication.android.musicplayerapp.domain.usecases.tracks.GetTracksByIdsUseCase
import javax.inject.Inject

class PlaylistDetailsLocalDI @Inject constructor(
    private val trackRepository: TracksRepository,
    private val playlistRepository: PlaylistRepository
) {

    private val getTracksByIdsUseCase by lazy { GetTracksByIdsUseCase(trackRepository) }
    
    private val deleteTrackFromPlaylistUseCase by lazy { DeleteTrackFromPlaylistUseCase(playlistRepository) }

    val actor by lazy { PlaylistDetailsActor(getTracksByIdsUseCase, deleteTrackFromPlaylistUseCase) }

    val reducer by lazy { PlaylistDetailsReducer() }

}