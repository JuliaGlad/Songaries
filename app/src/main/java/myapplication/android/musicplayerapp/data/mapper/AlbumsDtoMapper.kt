package myapplication.android.musicplayerapp.data.mapper

import myapplication.android.musicplayerapp.data.api.models.Album
import myapplication.android.musicplayerapp.data.api.models.AlbumList
import myapplication.android.musicplayerapp.data.repository.dto.AlbumDto
import myapplication.android.musicplayerapp.data.repository.dto.AlbumDtoList
import java.util.stream.Collectors

fun AlbumList.toDto() =
    AlbumDtoList(
        items = artists.albums
            .stream()
            .map { it.toDto() }
            .collect(Collectors.toList())
    )

fun Album.toDto() =
    AlbumDto(
        id = id,
        title = title,
        releaseDate = releaseDate,
        image = image
    )