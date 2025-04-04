package myapplication.android.musicplayerapp.domain.mapper

import myapplication.android.musicplayerapp.data.repository.dto.TrackDto
import myapplication.android.musicplayerapp.data.repository.dto.TrackDtoList
import myapplication.android.musicplayerapp.domain.models.TrackDomain
import myapplication.android.musicplayerapp.domain.models.TracksDomainList
import java.util.stream.Collectors

fun TrackDtoList.toDomain() =
    TracksDomainList(
        items.stream()
            .map { it.toDomain() }
            .collect(Collectors.toList())
    )

fun TrackDto.toDomain() =
    TrackDomain(
        id = id,
        title = title,
        image = image,
        audioUri = audioUri,
        artistId = artistId,
        artist = artist
    )