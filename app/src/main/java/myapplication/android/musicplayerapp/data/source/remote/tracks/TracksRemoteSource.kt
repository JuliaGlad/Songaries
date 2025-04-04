package myapplication.android.musicplayerapp.data.source.remote.tracks

import myapplication.android.musicplayerapp.data.api.models.TracksList

interface TracksRemoteSource {

    suspend fun getTracks(offset: Int): TracksList

    suspend fun getTracksByIds(ids: List<Int>): TracksList

    suspend fun getTracksByQuery(query: String): TracksList

    suspend fun getTracksByArtist(artistId: Int): TracksList
}