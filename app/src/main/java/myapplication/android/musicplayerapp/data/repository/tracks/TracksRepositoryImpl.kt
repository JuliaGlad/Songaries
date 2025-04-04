package myapplication.android.musicplayerapp.data.repository.tracks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import myapplication.android.musicplayerapp.data.mapper.toDto
import myapplication.android.musicplayerapp.data.repository.dto.TrackDtoList
import myapplication.android.musicplayerapp.data.source.local.tracks.TracksLocalSource
import myapplication.android.musicplayerapp.data.source.remote.tracks.TracksRemoteSource
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val remoteSource: TracksRemoteSource,
    private val localSource: TracksLocalSource
) : TracksRepository {
    override suspend fun getTracks(offset: Int): TrackDtoList {
        val result = withContext(Dispatchers.IO) {
            val local = localSource.getTracks(offset)
             if (local == null) {
                val remote = withContext(Dispatchers.IO) {
                    remoteSource.getTracks(offset)
                }
                localSource.deleteAll()
                localSource.insertAll(offset, remote)
                remote
            } else local

        }
        return result.toDto()
    }

    override suspend fun getTracksByIds(offset: Int, ids: List<Int>): TrackDtoList {
        val remote = withContext(Dispatchers.IO) {
            remoteSource.getTracksByIds(ids)
        }
        return remote.toDto()
    }

    override suspend fun getTracksByQuery(query: String, offset: Int): TrackDtoList {
        val local = localSource.getTracksByQuery(query)
        val result = if (local == null) {
            val remote = withContext(Dispatchers.IO) {
                remoteSource.getTracksByQuery(query)
            }
            localSource.deleteAll()
            localSource.insertAll(offset, remote)
            remote
        } else local
        return result.toDto()
    }

    override suspend fun getArtistTracks(artistId: Int): TrackDtoList {
        val local = localSource.getTracksByArtist(artistId)
        val result = if (local == null) {
            val remote = withContext(Dispatchers.IO) {
                remoteSource.getTracksByArtist(artistId)
            }
            localSource.deleteSeveral(remote.items.size)
            remote
        } else local
        return result.toDto()
    }
}