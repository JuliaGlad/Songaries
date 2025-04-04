package myapplication.android.musicplayerapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import myapplication.android.musicplayerapp.ui.navigation.screen.BottomScreen
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.screen.artists.ArtistsScreen
import myapplication.android.musicplayerapp.ui.screen.general.GeneralScreen
import myapplication.android.musicplayerapp.ui.screen.playlist.PlaylistScreen
import myapplication.android.musicplayerapp.ui.screen.playlist.new_playlist.NewPlaylistScreen

@Composable
fun Navigation(
    navController: NavHostController,
    paddings: PaddingValues
) {
    val viewmodel: NavigationViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = BottomScreen.GeneralScreen.route,
        modifier = Modifier.padding(paddings)
    ) {
        composable(route = BottomScreen.GeneralScreen.route) {
            GeneralScreen { viewmodel.setBottomBarVisibility(true) }
        }
        composable(route = BottomScreen.ArtistsScreen.route) {
            ArtistsScreen { viewmodel.setBottomBarVisibility(true) }
        }
        composable(route = BottomScreen.PlaylistsScreen.route) {
            PlaylistScreen(navController){ viewmodel.setBottomBarVisibility(true) }
        }
        composable(route = PlaylistsScreen.NewPlaylistScreen.route) {
            NewPlaylistScreen{ viewmodel.setBottomBarVisibility(false) }
        }
    }
}

fun withArgs(route: String, vararg args: String): String {
    return buildString {
        append(route)
        args.forEach { append("/$it") }
    }
}