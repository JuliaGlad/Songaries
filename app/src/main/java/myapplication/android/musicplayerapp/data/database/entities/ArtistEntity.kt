package myapplication.android.musicplayerapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val artistId: String,
    val offset: Int,
    val name: String,
    val website: String,
    val image: String
)