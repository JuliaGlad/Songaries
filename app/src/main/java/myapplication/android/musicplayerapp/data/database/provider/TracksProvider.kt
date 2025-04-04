package myapplication.android.musicplayerapp.data.database.provider

import myapplication.android.musicplayerapp.App.Companion.app
import myapplication.android.musicplayerapp.data.api.models.Track
import myapplication.android.musicplayerapp.data.api.models.TracksList
import myapplication.android.musicplayerapp.data.database.entities.TrackEntity

class TracksProvider {

    fun getTracksByArtist(artistId: Int): List<TrackEntity>? {
        val data = app.database.tracksDao().getTracks()
        var result: MutableList<TrackEntity>? = mutableListOf()
        if (data.isEmpty()) result = null
        else {
            for (i in data) {
                if (artistId == i.artistId.toInt()) {
                    result?.add(i)
                }
            }
        }
        return result
    }

    fun getTracks(offset: Int): List<TrackEntity>? {
        val data = app.database.tracksDao().getTracks()
        var result: MutableList<TrackEntity>? = mutableListOf()
        if (data.isEmpty()) result = null
        else {
            for (i in data) {
                if (offset == i.offset) {
                    result?.add(i)
                }
            }
        }
        return result
    }

    fun getTracksByQuery(query: String): List<TrackEntity>? {
        val data = app.database.tracksDao().getTracks()
        var result: MutableList<TrackEntity>? = mutableListOf()
        if (data.isEmpty()) result = null
        else {
            for (i in data) {
                if (i.title.lowercase().contains(query.lowercase())) {
                    result?.add(i)
                }
            }
        }
        return result
    }

    fun insertAll(offset: Int, trackList: TracksList) {
        val entities = mutableListOf<TrackEntity>()
        for (i in trackList.items) {
            with(i) {
                entities.add(
                    TrackEntity(
                        trackId = id,
                        offset = offset,
                        title = title,
                        image = image,
                        audioUri = audio,
                        artistId = artistId,
                        artist = artist
                    )
                )
            }
        }
        app.database.tracksDao().insertAll(entities)
    }

    fun insertTrack(offset: Int, track: Track) {
        with(track) {
            app.database.tracksDao().insertTracks(
                TrackEntity(
                    trackId = id,
                    offset = offset,
                    title = title,
                    image = image,
                    audioUri = audio,
                    artistId = artistId,
                    artist = artist
                )
            )
        }
    }


    fun deleteAll() {
        app.database.tracksDao().deleteAll()
    }

    fun deleteSeveral(size: Int) {
        val dao = app.database.tracksDao()
        val data = dao.getTracks()
        if (data.size >= 20) {
            for (i in data) {
                if (data.indexOf(i) >= size) break
                else dao.deleteTracks(i)
            }
        }
    }
}