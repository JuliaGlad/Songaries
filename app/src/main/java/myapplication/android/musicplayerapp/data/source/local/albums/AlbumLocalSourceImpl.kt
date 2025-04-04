package myapplication.android.musicplayerapp.data.source.local.albums

import myapplication.android.musicplayerapp.data.api.models.Album
import myapplication.android.musicplayerapp.data.api.models.AlbumList
import myapplication.android.musicplayerapp.data.api.models.ArtistsAlbumList
import myapplication.android.musicplayerapp.data.database.mapper.toAlbum
import myapplication.android.musicplayerapp.data.database.provider.AlbumsProvider
import java.util.stream.Collectors
import javax.inject.Inject

class AlbumLocalSourceImpl @Inject constructor(): AlbumLocalSource {

    override fun getAlbumById(id: Int): AlbumList? {
        val data = AlbumsProvider().getAlbumById(id)
        return if (data?.isNotEmpty() == true) {
            AlbumList(
                ArtistsAlbumList(data.stream()
                    .map { it.toAlbum() }
                    .collect(Collectors.toList()))
            )
        } else null
    }

    override fun insertAll(offset: Int, albumList: AlbumList) {
        AlbumsProvider().insertAll(offset, albumList)
    }

    override fun insertAlbums(offset: Int, album: Album) {
        AlbumsProvider().insertAlbums(offset, album)
    }

    override fun deleteAll() {
        AlbumsProvider().deleteAll()
    }

    override fun deleteSeveral(size: Int) {
        AlbumsProvider().deleteSeveral(size)
    }
}