package myapplication.android.musicplayerapp.data.repository.artisits

import myapplication.android.musicplayerapp.data.repository.dto.AlbumDtoList
import myapplication.android.musicplayerapp.data.repository.dto.ArtistsDtoList

interface ArtistsRepository {

    suspend fun getArtists(offset: Int): ArtistsDtoList

    suspend fun getArtistsByQuery(query: String, offset: Int): ArtistsDtoList

    suspend fun getArtistAlbums(artistId: Int): AlbumDtoList

}