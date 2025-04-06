package myapplication.android.musicplayerapp.ui.screen.playlist.main_playlists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import myapplication.android.musicplayerapp.ui.screen.playlist.main_playlists.di.LocalPlaylist
import myapplication.android.musicplayerapp.ui.screen.playlist.new_playlist.NewPlaylistScreen
import myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.PlaylistScreen
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPlaylistScreen(onBottomBarVisible: () -> Unit) {
    onBottomBarVisible()
    val viewmodel: MainPlaylistViewModel = hiltViewModel()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val playlists = viewmodel.playlistsState

    CompositionLocalProvider(
        LocalPlaylist provides playlists
    ) {
        PlaylistScreen(onShowBottomSheet = { showBottomSheet = true })
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                dragHandle = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MainGrey),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BottomSheetDefaults.DragHandle()
                    }
                }
            ) {
                NewPlaylistScreen(
                    onAddPlaylist = { playlists.add(it) },
                    onDismiss = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    })
            }
        }
    }
}