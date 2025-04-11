package myapplication.android.musicplayerapp.data.repository.tracks

import myapplication.android.musicplayerapp.data.repository.dto.TrackDtoList

interface TracksRepository {

    suspend fun getTracks(offset: Int): TrackDtoList

    suspend fun getTracksByIds( ids: List<Int>): TrackDtoList

    suspend fun getTracksByQuery(query: String, offset: Int): TrackDtoList

    suspend fun getArtistTracks(artistId: Int): TrackDtoList
}