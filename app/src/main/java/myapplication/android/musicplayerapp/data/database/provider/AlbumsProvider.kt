package myapplication.android.musicplayerapp.data.database.provider

import myapplication.android.musicplayerapp.App.Companion.app
import myapplication.android.musicplayerapp.data.api.models.Album
import myapplication.android.musicplayerapp.data.api.models.AlbumList
import myapplication.android.musicplayerapp.data.database.entities.AlbumEntity

class AlbumsProvider {

    fun getAlbumById(id: Int): List<AlbumEntity>? {
        val data = app.database.albumsDao().getAlbums()
        var result: MutableList<AlbumEntity>? = mutableListOf()
        if (data.isEmpty()) result = null
        else {
            for (i in data) {
                if (id == i.albumId.toInt()) {
                    result?.add(i)
                }
            }
        }
        return result
    }

    fun insertAll(offset: Int, albumList: AlbumList) {
        val entities = mutableListOf<AlbumEntity>()
        for (i in albumList.artists.albums) {
            with(i) {
                entities.add(
                    AlbumEntity(
                        albumId = id,
                        offset = offset,
                        title = title,
                        releaseDate = releaseDate,
                        image = image
                    )
                )
            }
        }
        app.database.albumsDao().insertAll(entities)
    }

    fun insertAlbums(offset: Int, album: Album) {
        with(album) {
            app.database.albumsDao().insertAlbum(
                AlbumEntity(
                    albumId = id,
                    offset = offset,
                    title = title,
                    releaseDate = releaseDate,
                    image = image
                )
            )
        }
    }


    fun deleteAll() {
        app.database.albumsDao().deleteAll()
    }

    fun deleteSeveral(size: Int) {
        val dao = app.database.albumsDao()
        val data = dao.getAlbums()
        if (data.size >= 20) {
            for (i in data) {
                if (data.indexOf(i) >= size) break
                else dao.deleteAlbum(i)
            }
        }
    }
}