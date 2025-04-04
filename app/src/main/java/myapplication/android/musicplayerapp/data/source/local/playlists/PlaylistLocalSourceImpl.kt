package myapplication.android.musicplayerapp.data.source.local.playlists

import myapplication.android.musicplayerapp.data.database.provider.PlaylistsProvider
import myapplication.android.musicplayerapp.data.mapper.toDto
import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDtoList
import javax.inject.Inject


class PlaylistLocalSourceImpl @Inject constructor(): PlaylistLocalSource {
    override fun getPlaylists(): PlaylistDtoList? {
        val data = PlaylistsProvider().getPlaylists()
        return if (data.isNotEmpty()){
            data.toDto()
        } else null
    }

    override fun addNewTrack(title: String, id: Int) {
        PlaylistsProvider().addNewTrack(title, id)
    }

    override fun deleteTrack(title: String, id: Int) {
        PlaylistsProvider().removeTrack(title, id)
    }

    override fun insertPlaylist(
        title: String,
        image: String,
        description: String,
        tracks: MutableList<Int>
    ) {
       PlaylistsProvider().insertPlaylist(title, image, description, tracks)
    }

    override fun deleteAll() {
        PlaylistsProvider().deleteAll()
    }

    override fun deletePlaylist(title: String) {
        PlaylistsProvider().deletePlaylist(title)
    }


}