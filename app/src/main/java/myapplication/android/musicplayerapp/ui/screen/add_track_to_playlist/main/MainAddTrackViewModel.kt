package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.main

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi
import javax.inject.Inject

@HiltViewModel
class MainAddTrackViewModel @Inject constructor(
    localPlaylist: SnapshotStateList<PlaylistUi>
): ViewModel() {
    val playlistState: SnapshotStateList<PlaylistUi> = localPlaylist
}