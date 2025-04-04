package myapplication.android.musicplayerapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val trackId: String,
    val offset: Int,
    val title: String,
    val image: String,
    val audioUri: String,
    val artistId: String,
    val artist: String
)