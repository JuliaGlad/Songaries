package myapplication.android.musicplayerapp.ui.screen.playlist.main_playlists.di

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi

val LocalPlaylist = compositionLocalOf<SnapshotStateList<PlaylistUi>> { mutableStateListOf() }