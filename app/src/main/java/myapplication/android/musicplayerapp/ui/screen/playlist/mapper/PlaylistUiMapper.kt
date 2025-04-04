package myapplication.android.musicplayerapp.ui.screen.playlist.mapper

import myapplication.android.musicplayerapp.domain.models.PlaylistDomain
import myapplication.android.musicplayerapp.domain.models.PlaylistsDomainList
import myapplication.android.musicplayerapp.ui.screen.playlist.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.screen.playlist.model.PlaylistUiList
import java.util.stream.Collectors

fun PlaylistsDomainList.toUi() =
    PlaylistUiList(
        items.stream()
            .map { it.toUi() }
            .collect(Collectors.toList())
    )

fun PlaylistDomain.toUi() =
    PlaylistUi(
        title = title,
        image = image,
        description = description,
        tracks = tracks
    )