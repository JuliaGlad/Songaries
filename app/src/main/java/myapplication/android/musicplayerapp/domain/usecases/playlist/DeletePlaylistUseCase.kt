package myapplication.android.musicplayerapp.domain.usecases.playlist

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import javax.inject.Inject

class DeletePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
){
    suspend fun invoke(title: String) = repository.deletePlaylist(title)
}