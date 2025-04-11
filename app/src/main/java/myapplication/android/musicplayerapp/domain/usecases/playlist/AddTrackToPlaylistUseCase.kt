package myapplication.android.musicplayerapp.domain.usecases.playlist

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import javax.inject.Inject

class AddTrackToPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
){
    suspend fun invoke(
        playlistTitle: String,
        id: String,
        title: String,
        image: String,
        audio: String,
        artistsId: String,
        artist: String
    ) = repository.addTracksToPlaylist(playlistTitle, id, title, image, audio, artistsId, artist)
}