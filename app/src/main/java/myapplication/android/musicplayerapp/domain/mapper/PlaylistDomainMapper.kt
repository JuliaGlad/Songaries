package myapplication.android.musicplayerapp.domain.mapper

import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDto
import myapplication.android.musicplayerapp.data.repository.dto.PlaylistDtoList
import myapplication.android.musicplayerapp.domain.models.PlaylistDomain
import myapplication.android.musicplayerapp.domain.models.PlaylistsDomainList
import java.util.stream.Collectors

fun PlaylistDtoList.toDomain() =
    PlaylistsDomainList(
        items.stream()
            .map { it.toDomain() }
            .collect(Collectors.toList())
    )

fun PlaylistDto.toDomain() =
    PlaylistDomain(
        title = title,
        image = image,
        description = description,
        tracks = tracks.toDomain()
    )