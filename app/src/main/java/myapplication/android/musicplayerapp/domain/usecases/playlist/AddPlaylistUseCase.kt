package myapplication.android.musicplayerapp.domain.usecases.playlist

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import javax.inject.Inject

class AddPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend fun invoke(
        title: String,
        image: String,
        description: String
    ) = repository.addPlaylist(title, image, description)
}