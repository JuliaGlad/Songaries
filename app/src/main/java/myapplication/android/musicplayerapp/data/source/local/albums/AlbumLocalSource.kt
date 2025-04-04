package myapplication.android.musicplayerapp.data.source.local.albums

import myapplication.android.musicplayerapp.data.api.models.Album
import myapplication.android.musicplayerapp.data.api.models.AlbumList

interface AlbumLocalSource {

    fun getAlbumById(id: Int): AlbumList?

    fun insertAll(offset: Int, albumList: AlbumList)

    fun insertAlbums(offset: Int, album: Album)

    fun deleteAll()

    fun deleteSeveral(size: Int)

}