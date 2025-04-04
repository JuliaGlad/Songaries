package myapplication.android.musicplayerapp.ui.screen.mapper

import myapplication.android.musicplayerapp.domain.models.TrackDomain
import myapplication.android.musicplayerapp.domain.models.TracksDomainList
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiModel
import java.util.stream.Collectors

fun TracksDomainList.toUi() =
    TrackUiList(
        items.stream()
            .map { it.toUi() }
            .collect(Collectors.toList())
    )

fun TrackDomain.toUi() =
    TrackUiModel(
        trackId = id,
        title = title,
        image = image,
        audioUri = audioUri,
        artistId = artistId,
        artist = artist
    )