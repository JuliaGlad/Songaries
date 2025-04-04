package myapplication.android.musicplayerapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import myapplication.android.musicplayerapp.data.database.entities.PlaylistEntity

@Dao
interface PlaylistDao {

    @Query("SELECT * FROM playlists")
    fun getPlaylists(): List<PlaylistEntity>

    @Insert
    fun insertPlaylist(playlist: PlaylistEntity)

    @Update
    fun updatePlaylist(playlist: PlaylistEntity)

    @Query("DELETE FROM playlists")
    fun deleteAll()

    @Delete
    fun deletePlaylist(playlist: PlaylistEntity)

}