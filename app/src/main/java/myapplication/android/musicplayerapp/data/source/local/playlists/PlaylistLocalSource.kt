package myapplication.android.musicplayerapp.data.source.local.playlists

import myapplication.android.musicplayerapp.data.api.models.Track
import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDtoList

interface PlaylistLocalSource {

    fun getPlaylists(): PlaylistDtoList?

    fun addNewTrack(
        playlistTitle: String,
        id: String,
        title: String,
        image: String,
        audio: String,
        artistsId: String,
        artist: String
    )

    fun deleteTrack(title: String, id: Int)

    fun insertPlaylist(
        title: String,
        image: String,
        description: String,
        tracks: MutableList<Track>
    )
    
    fun deleteAll()

    fun deletePlaylist(title: String)

}