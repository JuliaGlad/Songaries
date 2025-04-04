package myapplication.android.musicplayerapp.domain.usecases.playlist

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import javax.inject.Inject

class DeleteTrackFromPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
){
    suspend fun invoke(title: String, id: Int) =
        repository.deleteTrackFromPlaylist(title, id)
}