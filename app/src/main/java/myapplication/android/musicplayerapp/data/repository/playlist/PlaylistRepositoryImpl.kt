package myapplication.android.musicplayerapp.data.repository.playlist

import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDtoList
import myapplication.android.musicplayerapp.data.source.local.playlists.PlaylistLocalSource
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val localSource: PlaylistLocalSource
) : PlaylistRepository {

    override suspend fun getPlaylists(): PlaylistDtoList {
        val local = localSource.getPlaylists()
        return local ?: PlaylistDtoList(emptyList())
    }

    override suspend fun addTracksToPlaylist(
        playlistTitle: String,
        id: String,
        title: String,
        image: String,
        audio: String,
        artistsId: String,
        artist: String
    ) {
        localSource.addNewTrack(playlistTitle, id, title, image, audio, artistsId, artist)
    }

    override suspend fun deleteTrackFromPlaylist(playlistTitle: String, trackId: Int) {
        localSource.deleteTrack(playlistTitle, trackId)
    }

    override suspend fun addPlaylist(title: String, image: String, description: String) {
        localSource.insertPlaylist(
            title = title,
            image = image,
            description = description,
            tracks = mutableListOf()
        )
    }

    override suspend fun deletePlaylist(title: String) {
        localSource.deletePlaylist(title)
    }

}