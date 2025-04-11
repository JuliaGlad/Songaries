package myapplication.android.musicplayerapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import myapplication.android.musicplayerapp.data.api.models.Track

@Entity(tableName = "playlists")
class PlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val image: String,
    val description: String,
    val tracks: MutableList<Track>
)