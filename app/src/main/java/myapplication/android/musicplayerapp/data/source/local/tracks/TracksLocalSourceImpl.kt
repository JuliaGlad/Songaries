package myapplication.android.musicplayerapp.data.source.local.tracks

import myapplication.android.musicplayerapp.data.api.models.Track
import myapplication.android.musicplayerapp.data.api.models.TracksList
import myapplication.android.musicplayerapp.data.database.mapper.toTrack
import myapplication.android.musicplayerapp.data.database.provider.TracksProvider
import java.util.stream.Collectors
import javax.inject.Inject

class TracksLocalSourceImpl @Inject constructor() : TracksLocalSource {
    override fun getTracksByArtist(artistId: Int): TracksList? {
        val data = TracksProvider().getTracksByArtist(artistId)
        return if (data?.isNotEmpty() == true) {
            TracksList(
                data.stream()
                    .map { it.toTrack() }
                    .collect(Collectors.toList())
            )
        } else null
    }

    override fun getTracks(offset: Int): TracksList? {
        val data = TracksProvider().getTracks(offset)
        return if (data?.isNotEmpty() == true) {
            TracksList(
                data.stream()
                    .map { it.toTrack() }
                    .collect(Collectors.toList())
            )
        } else null
    }

    override fun getTracksByQuery(query: String): TracksList? {
        val data = TracksProvider().getTracksByQuery(query)
        return if (data?.isNotEmpty() == true) {
            TracksList(
                data.stream()
                    .map { it.toTrack() }
                    .collect(Collectors.toList())
            )
        } else null
    }

    override fun insertAll(offset: Int, trackList: TracksList) {
        TracksProvider().insertAll(offset, trackList)
    }

    override fun insertTrack(offset: Int, track: Track) {
        TracksProvider().insertTrack(offset, track)
    }

    override fun deleteAll() {
        TracksProvider().deleteAll()
    }

    override fun deleteSeveral(size: Int) {
        TracksProvider().deleteSeveral(size)
    }

}