package myapplication.android.musicplayerapp.data.repository.artisits

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import myapplication.android.musicplayerapp.data.mapper.toDto
import myapplication.android.musicplayerapp.data.repository.dto.AlbumDtoList
import myapplication.android.musicplayerapp.data.repository.dto.ArtistsDtoList
import myapplication.android.musicplayerapp.data.source.local.albums.AlbumLocalSource
import myapplication.android.musicplayerapp.data.source.local.artists.ArtistsLocalSource
import myapplication.android.musicplayerapp.data.source.remote.artists.ArtistsRemoteSource
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    private val remoteSource: ArtistsRemoteSource,
    private val artistLocalSource: ArtistsLocalSource,
    private val albumLocalSource: AlbumLocalSource
) : ArtistsRepository {
    override suspend fun getArtists(offset: Int): ArtistsDtoList {
        val local = artistLocalSource.getArtists(offset)
        val result = if (local == null) {
            val remote = withContext(Dispatchers.IO){
                remoteSource.getArtists(offset)
            }
            artistLocalSource.deleteAll()
            artistLocalSource.insertAll(offset, remote)
            remote
        } else local
        return result.toDto()
    }

    override suspend fun getArtistsByQuery(query: String, offset: Int): ArtistsDtoList {
        val local = artistLocalSource.getArtistsByQuery(query)
        val result = if (local == null) {
            val remote = withContext(Dispatchers.IO){
                remoteSource.getArtistsByQuery(query)
            }
            artistLocalSource.deleteAll()
            artistLocalSource.insertAll(offset, remote)
            remote
        } else local
        return result.toDto()
    }

    override suspend fun getArtistAlbums(artistId: Int): AlbumDtoList {
        val local = albumLocalSource.getAlbumById(artistId)
        val result = if (local == null) {
            val remote = withContext(Dispatchers.IO){
                remoteSource.getArtistsAlbums(artistId)
            }
            albumLocalSource.deleteSeveral(remote.artists.albums.size)
            remote
        } else local
        return result.toDto()
    }

}