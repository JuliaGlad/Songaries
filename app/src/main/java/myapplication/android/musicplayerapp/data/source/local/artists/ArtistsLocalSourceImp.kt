package myapplication.android.musicplayerapp.data.source.local.artists

import myapplication.android.musicplayerapp.data.api.models.Artist
import myapplication.android.musicplayerapp.data.api.models.ArtistsList
import myapplication.android.musicplayerapp.data.database.mapper.toArtist
import myapplication.android.musicplayerapp.data.database.provider.ArtistsProvider
import java.util.stream.Collectors
import javax.inject.Inject

class ArtistsLocalSourceImpl @Inject constructor(): ArtistsLocalSource {
    override fun getArtists(offset: Int): ArtistsList? {
        val data = ArtistsProvider().getArtists(offset)
        return if (data?.isNotEmpty() == true) {
            ArtistsList(
                data.stream()
                    .map { it.toArtist() }
                    .collect(Collectors.toList())
            )
        } else null
    }

    override fun getArtistsByQuery(query: String): ArtistsList? {
        val data = ArtistsProvider().getArtistsByQuery(query)
        return if (data?.isNotEmpty() == true) {
            ArtistsList(
                data.stream()
                    .map { it.toArtist() }
                    .collect(Collectors.toList())
            )
        } else null
    }

    override fun getArtistById(id: Int): Artist? =
        ArtistsProvider().getArtistById(id)?.toArtist()

    override fun insertAll(offset: Int, artistsList: ArtistsList) {
        ArtistsProvider().insertAll(offset, artistsList)
    }

    override fun insertArtists(offset: Int, artists: Artist) {
        ArtistsProvider().insertArtist(offset, artists)
    }

    override fun deleteAll() {
        ArtistsProvider().deleteAll()
    }

    override fun deleteSeveral(size: Int) {
        ArtistsProvider().deleteSeveral(size)
    }
}