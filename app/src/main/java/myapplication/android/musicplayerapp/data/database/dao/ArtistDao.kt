package myapplication.android.musicplayerapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import myapplication.android.musicplayerapp.data.database.entities.ArtistEntity

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artists")
    fun getArtists(): List<ArtistEntity>

    @Insert
    fun insertAll(artistsList: List<ArtistEntity>)

    @Insert
    fun insertArtist(artist: ArtistEntity)

    @Query("DELETE FROM artists")
    fun deleteAll()

    @Delete
    fun deleteArtist(artist: ArtistEntity)

}