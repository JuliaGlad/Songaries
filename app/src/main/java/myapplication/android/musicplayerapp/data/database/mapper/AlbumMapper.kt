package myapplication.android.musicplayerapp.data.database.mapper

import myapplication.android.musicplayerapp.data.api.models.Album
import myapplication.android.musicplayerapp.data.database.entities.AlbumEntity

fun AlbumEntity.toAlbum() =
    Album(
        id = albumId,
        title = title,
        releaseDate = releaseDate,
        image = image
    )