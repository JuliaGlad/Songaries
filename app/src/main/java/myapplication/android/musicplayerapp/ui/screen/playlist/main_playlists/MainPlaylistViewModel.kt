package myapplication.android.musicplayerapp.ui.screen.playlist.main_playlists

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi
import javax.inject.Inject

@HiltViewModel
class MainPlaylistViewModel @Inject constructor(
    localPlaylists: SnapshotStateList<PlaylistUi>
): ViewModel() {

    val playlistsState: SnapshotStateList<PlaylistUi> = localPlaylists

}