package myapplication.android.musicplayerapp.data.repository.playlist

import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDtoList

interface PlaylistRepository {

    suspend fun getPlaylists(): PlaylistDtoList

    suspend fun addTracksToPlaylist(
        playlistTitle: String,
        id: String,
        title: String,
        image: String,
        audio: String,
        artistsId: String,
        artist: String
    )

    suspend fun deleteTrackFromPlaylist(playlistTitle: String, trackId: Int)

    suspend fun addPlaylist(
        title: String,
        image: String,
        description: String
    )

    suspend fun deletePlaylist(title: String)

}