package myapplication.android.musicplayerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import myapplication.android.musicplayerapp.data.database.converter.IntegerListConverter
import myapplication.android.musicplayerapp.data.database.dao.AlbumsDao
import myapplication.android.musicplayerapp.data.database.dao.ArtistDao
import myapplication.android.musicplayerapp.data.database.dao.PlaylistDao
import myapplication.android.musicplayerapp.data.database.dao.TracksDao
import myapplication.android.musicplayerapp.data.database.entities.AlbumEntity
import myapplication.android.musicplayerapp.data.database.entities.ArtistEntity
import myapplication.android.musicplayerapp.data.database.entities.PlaylistEntity
import myapplication.android.musicplayerapp.data.database.entities.TrackEntity

@TypeConverters(
    value = [
        IntegerListConverter::class
    ]
)
@Database(
    entities = [
        TrackEntity::class,
        ArtistEntity::class,
        AlbumEntity::class,
        PlaylistEntity::class
    ], version = 1
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun tracksDao(): TracksDao

    abstract fun artistDao(): ArtistDao

    abstract fun albumsDao(): AlbumsDao

    abstract fun playlistDao(): PlaylistDao
}