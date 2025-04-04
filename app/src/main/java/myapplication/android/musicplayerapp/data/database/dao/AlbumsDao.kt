package myapplication.android.musicplayerapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import myapplication.android.musicplayerapp.data.database.entities.AlbumEntity

@Dao
interface AlbumsDao {

    @Query("SELECT * FROM albums")
    fun getAlbums(): List<AlbumEntity>

    @Insert
    fun insertAll(albumsList: List<AlbumEntity>)

    @Insert
    fun insertAlbum(album: AlbumEntity)

    @Query("DELETE FROM albums")
    fun deleteAll()

    @Delete
    fun deleteAlbum(album: AlbumEntity)

}