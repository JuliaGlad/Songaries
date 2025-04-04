package myapplication.android.musicplayerapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val albumId: String,
    val offset: Int,
    val title: String,
    val releaseDate: String,
    val image: String
)