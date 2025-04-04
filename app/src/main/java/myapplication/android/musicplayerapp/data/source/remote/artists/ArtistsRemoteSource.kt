package myapplication.android.musicplayerapp.data.source.remote.artists

import myapplication.android.musicplayerapp.data.api.models.AlbumList
import myapplication.android.musicplayerapp.data.api.models.ArtistsList

interface ArtistsRemoteSource {

    suspend fun getArtists(offset: Int): ArtistsList

    suspend fun getArtistsByQuery(query: String): ArtistsList

    suspend fun getArtistsAlbums(artistId: Int): AlbumList
}