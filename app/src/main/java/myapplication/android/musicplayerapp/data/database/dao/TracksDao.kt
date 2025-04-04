package myapplication.android.musicplayerapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import myapplication.android.musicplayerapp.data.database.entities.TrackEntity

@Dao
interface TracksDao {

    @Query("SELECT * FROM tracks")
    fun getTracks(): List<TrackEntity>

    @Insert
    fun insertAll(tracksList: List<TrackEntity>)

    @Insert
    fun insertTracks(track: TrackEntity)

    @Query("DELETE FROM tracks")
    fun deleteAll()

    @Delete
    fun deleteTracks(track: TrackEntity)

}