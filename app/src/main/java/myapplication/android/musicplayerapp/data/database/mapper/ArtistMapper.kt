package myapplication.android.musicplayerapp.data.database.mapper

import myapplication.android.musicplayerapp.data.api.models.Artist
import myapplication.android.musicplayerapp.data.database.entities.ArtistEntity

fun ArtistEntity.toArtist() =
    Artist(
        id = artistId,
        name = name,
        website = website,
        image = image
    )