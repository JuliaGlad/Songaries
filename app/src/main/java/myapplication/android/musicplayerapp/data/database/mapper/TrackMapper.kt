package myapplication.android.musicplayerapp.data.database.mapper

import myapplication.android.musicplayerapp.data.api.models.Track
import myapplication.android.musicplayerapp.data.database.entities.TrackEntity

fun TrackEntity.toTrack() =
    Track(
        id = trackId,
        title = title,
        image = image,
        audio = audioUri,
        artistId = artistId,
        artist = artist
    )