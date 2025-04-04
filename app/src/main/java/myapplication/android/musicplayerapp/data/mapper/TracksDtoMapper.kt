package myapplication.android.musicplayerapp.data.mapper

import myapplication.android.musicplayerapp.data.api.models.Track
import myapplication.android.musicplayerapp.data.api.models.TracksList
import myapplication.android.musicplayerapp.data.repository.dto.TrackDto
import myapplication.android.musicplayerapp.data.repository.dto.TrackDtoList
import java.util.stream.Collectors

fun TracksList.toDto() =
    TrackDtoList(
        items = items
            .stream()
            .map { it.toDto() }
            .collect(Collectors.toList())
    )

fun Track.toDto() =
    TrackDto(
        id = id,
        title = title,
        image = image,
        audioUri = audio,
        artistId = artistId,
        artist = artist
    )