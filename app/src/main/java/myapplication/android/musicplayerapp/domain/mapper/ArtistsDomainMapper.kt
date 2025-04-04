package myapplication.android.musicplayerapp.domain.mapper

import myapplication.android.musicplayerapp.data.repository.dto.ArtistDto
import myapplication.android.musicplayerapp.data.repository.dto.ArtistsDtoList
import myapplication.android.musicplayerapp.domain.models.ArtistDomain
import myapplication.android.musicplayerapp.domain.models.ArtistsDomainList
import java.util.stream.Collectors

fun ArtistsDtoList.toDomain() =
    ArtistsDomainList(
        items = items.stream()
            .map { it.toDomain() }
            .collect(Collectors.toList())
    )

fun ArtistDto.toDomain() =
    ArtistDomain(
        id = id,
        name = name,
        website = website,
        image = image
    )