package myapplication.android.musicplayerapp.domain.mapper

import myapplication.android.musicplayerapp.data.repository.dto.AlbumDto
import myapplication.android.musicplayerapp.data.repository.dto.AlbumDtoList
import myapplication.android.musicplayerapp.domain.models.AlbumDomain
import myapplication.android.musicplayerapp.domain.models.AlbumsDomainList
import java.util.stream.Collectors

fun AlbumDtoList.toDomain() =
    AlbumsDomainList(
        items.stream()
            .map { it.toDomain() }
            .collect(Collectors.toList())
    )

fun AlbumDto.toDomain() =
    AlbumDomain(
        id = id,
        title = title,
        image = image,
        releaseDate = releaseDate
    )