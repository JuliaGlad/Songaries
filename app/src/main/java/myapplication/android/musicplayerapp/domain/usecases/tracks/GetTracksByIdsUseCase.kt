package myapplication.android.musicplayerapp.domain.usecases.tracks

import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.domain.mapper.toDomain
import myapplication.android.musicplayerapp.domain.models.TracksDomainList
import javax.inject.Inject

class GetTracksByIdsUseCase @Inject constructor(
    private val repository: TracksRepository
) {
    suspend fun invoke(offset: Int, ids: List<Int>): TracksDomainList =
        repository.getTracksByIds(offset, ids).toDomain()
}