package myapplication.android.musicplayerapp.data.source.remote.artists

import myapplication.android.musicplayerapp.data.api.MusicApi
import myapplication.android.musicplayerapp.data.api.models.AlbumList
import myapplication.android.musicplayerapp.data.api.models.ArtistsList
import myapplication.android.musicplayerapp.data.source.remote.artists.ArtistsRemoteSource
import javax.inject.Inject

class ArtistsRemoteSourceImpl @Inject constructor(
    private val api: MusicApi
) : ArtistsRemoteSource {
    override suspend fun getArtists(offset: Int): ArtistsList =
        api.getArtists(offset)

    override suspend fun getArtistsByQuery(query: String): ArtistsList =
        api.getArtistsByQuery(query)

    override suspend fun getArtistsAlbums(artistId: Int): AlbumList =
        api.getAlbumsByArtist(artistId)
}