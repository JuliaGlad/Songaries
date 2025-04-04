package myapplication.android.musicplayerapp.data.source.remote.tracks

import myapplication.android.musicplayerapp.data.api.MusicApi
import myapplication.android.musicplayerapp.data.api.models.TracksList
import myapplication.android.musicplayerapp.data.source.remote.tracks.TracksRemoteSource
import javax.inject.Inject

class TracksRemoteSourceImpl @Inject constructor(
    private val api: MusicApi
): TracksRemoteSource {
    override suspend fun getTracks(offset: Int): TracksList =
        api.getTracks(offset)

    override suspend fun getTracksByIds(ids: List<Int>): TracksList =
        api.getTracksByIds(ids)

    override suspend fun getTracksByQuery(query: String): TracksList =
        api.getTracksByQuery(query)

    override suspend fun getTracksByArtist(artistId: Int): TracksList =
        api.getArtistTracks(artistId)
}