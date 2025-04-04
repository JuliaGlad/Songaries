package myapplication.android.musicplayerapp.data.mapper

import myapplication.android.musicplayerapp.data.database.entities.PlaylistEntity
import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDto
import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDtoList
import java.util.stream.Collectors

fun List<PlaylistEntity>.toDto() =
    PlaylistDtoList(
        items = stream()
            .map { it.toDto() }
            .collect(Collectors.toList())
    )

fun PlaylistEntity.toDto() =
    PlaylistDto(
        title = title,
        image = image,
        description = description,
        tracks = tracks
    )