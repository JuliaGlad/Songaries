package myapplication.android.musicplayerapp.data.source.local.tracks

import myapplication.android.musicplayerapp.data.api.models.Track
import myapplication.android.musicplayerapp.data.api.models.TracksList

interface TracksLocalSource {

    fun getTracksByArtist(artistId: Int): TracksList?

    fun getTracks(offset: Int): TracksList?

    fun getTracksByQuery(query: String): TracksList?

    fun insertAll(offset: Int, trackList: TracksList)

    fun insertTrack(offset: Int, track: Track)

    fun deleteAll()

    fun deleteSeveral(size: Int)

}