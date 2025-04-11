package myapplication.android.musicplayerapp.data.database.provider

import myapplication.android.musicplayerapp.App.Companion.app
import myapplication.android.musicplayerapp.data.api.models.Track
import myapplication.android.musicplayerapp.data.database.entities.PlaylistEntity

class PlaylistsProvider {

    fun addNewTrack(title: String, track: Track){
        val dao = app.database.playlistDao()
        val data = dao.getPlaylists()
        for (i in data){
            if (i.title == title){
                i.tracks.add(track)
                dao.updatePlaylist(i)
                break
            }
        }
    }

    fun removeTrack(title: String, id: Int){
        val dao = app.database.playlistDao()
        val data = dao.getPlaylists()
        for (i in data){
            if (i.title == title){
                for (track in i.tracks){
                    if (track.id == id.toString()){
                        i.tracks.remove(track)
                        dao.updatePlaylist(i)
                        break
                    }
                }
            }
        }
    }

    fun getPlaylists(): List<PlaylistEntity> {
        return app.database.playlistDao().getPlaylists()
    }

    fun insertPlaylist(
        title: String,
        image: String,
        description: String,
        tracks: MutableList<Track>
    ) {
        app.database.playlistDao().insertPlaylist(
            PlaylistEntity(
                title = title,
                image = image,
                description = description,
                tracks = tracks
            )
        )
    }
    
    fun deleteAll() {
        app.database.playlistDao().deleteAll()
    }

    fun deletePlaylist(title: String) {
        val dao = app.database.playlistDao()
        val data = dao.getPlaylists()
        for (i in data){
            if (i.title == title){
                dao.deletePlaylist(i)
                break
            }
        }
    }

}