package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.main

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
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import myapplication.android.musicplayerapp.ui.local_composition.LocalPlaylist
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.AddTrackToPlaylistScreen
import myapplication.android.musicplayerapp.ui.screen.new_playlist.NewPlaylistScreen
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAddTrackScreen(
    navController: NavController,
    trackId: String?,
    onBottomBarVisible: () -> Unit
) {
    val viewModel: MainAddTrackViewModel = hiltViewModel()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val playlists = viewModel.playlistState

    CompositionLocalProvider(
        LocalPlaylist provides playlists
    ) {
        AddTrackToPlaylistScreen(
            trackId,
            navigateBack = { navController.popBackStack() },
            openAddPlaylist = { showBottomSheet = true }
        ) { onBottomBarVisible() }
        if (showBottomSheet){
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
                            if (!sheetState.isVisible){
                                showBottomSheet = false
                            }
                        }
                    }
                )
            }
        }
    }
}