package myapplication.android.musicplayerapp.data.source.local.playlists

import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDtoList

interface PlaylistLocalSource {

    fun getPlaylists(): PlaylistDtoList?

    fun addNewTrack(title: String, id: Int)

    fun deleteTrack(title: String, id: Int)

    fun insertPlaylist(
        title: String,
        image: String,
        description: String,
        tracks: MutableList<Int>
    )
    
    fun deleteAll()

    fun deletePlaylist(title: String)

}