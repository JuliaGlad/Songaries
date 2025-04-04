package myapplication.android.musicplayerapp.domain.usecases.tracks

import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.domain.mapper.toDomain
import myapplication.android.musicplayerapp.domain.models.TracksDomainList
import javax.inject.Inject

class GetArtistTracksUseCase @Inject constructor(
    private val repository: TracksRepository
) {
    suspend fun invoke(artistId: Int) : TracksDomainList =
        repository.getArtistTracks(artistId).toDomain()
}