package myapplication.android.musicplayerapp.data.database.provider

import myapplication.android.musicplayerapp.App.Companion.app
import myapplication.android.musicplayerapp.data.api.models.Artist
import myapplication.android.musicplayerapp.data.api.models.ArtistsList
import myapplication.android.musicplayerapp.data.database.entities.ArtistEntity

class ArtistsProvider {
    fun getArtists(offset: Int): List<ArtistEntity>? {
        val data = app.database.artistDao().getArtists()
        var result: MutableList<ArtistEntity>? = mutableListOf()
        if (data.isEmpty()) result = null
        else {
            for (i in data) {
                if (i.offset == offset) {
                    result?.add(i)
                }
            }
        }
        return result
    }

    fun getArtistsByQuery(query: String): List<ArtistEntity>? {
        val data = app.database.artistDao().getArtists()
        var result: MutableList<ArtistEntity>? = mutableListOf()
        if (data.isEmpty()) result = null
        else {
            for (i in data) {
                if (i.name.lowercase().contains(query.lowercase())) {
                    result?.add(i)
                }
            }
        }
        return result
    }

    fun getArtistById(id: Int): ArtistEntity? {
        val data = app.database.artistDao().getArtists()
        var result: ArtistEntity? = null
        if (data.isNotEmpty()) {
            for (i in data) {
                if (i.artistId.toInt() == id) {
                    result = i
                }
            }
        }
        return result
    }

    fun insertAll(offset: Int, artistsList: ArtistsList) {
        val entities = mutableListOf<ArtistEntity>()
        for (i in artistsList.items) {
            with(i) {
                entities.add(
                    ArtistEntity(
                        artistId = id,
                        offset = offset,
                        name = name,
                        website = website,
                        image = image
                    )
                )
            }
        }
        app.database.artistDao().insertAll(entities)
    }

    fun insertArtist(offset: Int, artist: Artist) {
        with(artist) {
            app.database.artistDao().insertArtist(
                ArtistEntity(
                    artistId = id,
                    offset = offset,
                    name = name,
                    website = website,
                    image = image
                )
            )
        }
    }


    fun deleteAll() {
        app.database.artistDao().deleteAll()
    }

    fun deleteSeveral(size: Int) {
        val dao = app.database.artistDao()
        val data = dao.getArtists()
        if (data.size >= 20) {
            for (i in data) {
                if (data.indexOf(i) >= size) break
                else dao.deleteArtist(i)
            }
        }
    }
}