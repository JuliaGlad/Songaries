package myapplication.android.musicapp.domain.usecases.playlist

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
class AddTrackToPlaylistUseCase constructor(
    private val repository: PlaylistRepository
){
    suspend fun invoke(title: String, id: Int) =
        repository.addTracksToPlaylist(title, id)
}