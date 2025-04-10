package myapplication.android.musicplayerapp.ui.screen.playlist.main_playlists

import android.net.Uri
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
import com.google.gson.Gson
import kotlinx.coroutines.launch
import myapplication.android.musicplayerapp.ui.local_composition.LocalPlaylist
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.navigation.withArgs
import myapplication.android.musicplayerapp.ui.screen.new_playlist.NewPlaylistScreen
import myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.PlaylistScreen
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPlaylistScreen(navController: NavController, onBottomBarVisible: () -> Unit) {
    onBottomBarVisible()
    val viewmodel: MainPlaylistViewModel = hiltViewModel()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val playlists = viewmodel.playlistsState

    CompositionLocalProvider(
        LocalPlaylist provides playlists
    ) {
        PlaylistScreen(
            openPlaylistDetailsScreen = { title, ids ->
                navController.navigate(
                    withArgs(
                        route = PlaylistsScreen.PlaylistDetailsScreen.route,
                           title, Uri.encode(Gson().toJson(ids))
                    )
                )
            },
            onShowBottomSheet = { showBottomSheet = true }
        )
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