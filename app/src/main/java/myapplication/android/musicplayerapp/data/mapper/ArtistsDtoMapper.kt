package myapplication.android.musicplayerapp.data.mapper

import myapplication.android.musicplayerapp.data.api.models.Artist
import myapplication.android.musicplayerapp.data.api.models.ArtistsList
import myapplication.android.musicplayerapp.data.repository.dto.ArtistDto
import myapplication.android.musicplayerapp.data.repository.dto.ArtistsDtoList
import java.util.stream.Collectors

fun ArtistsList.toDto() =
    ArtistsDtoList(
        items = items
            .stream()
            .map { it.toDto() }
            .collect(Collectors.toList())
    )

fun Artist.toDto() =
    ArtistDto(
        id = id,
        name = name,
        website = website,
        image = image
    )
