package myapplication.android.musicplayerapp.data.source.local.artists

import myapplication.android.musicplayerapp.data.api.models.Artist
import myapplication.android.musicplayerapp.data.api.models.ArtistsList

interface ArtistsLocalSource {

    fun getArtists(offset: Int): ArtistsList?

    fun getArtistsByQuery(query: String): ArtistsList?

    fun getArtistById(id: Int): Artist?

    fun insertAll(offset: Int, artistsList: ArtistsList)

    fun insertArtists(offset: Int, artists: Artist)

    fun deleteAll()

    fun deleteSeveral(size: Int)
}