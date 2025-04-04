package myapplication.android.musicplayerapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.navigation.BottomNavigationBar
import myapplication.android.musicplayerapp.ui.navigation.BottomNavigationItem
import myapplication.android.musicplayerapp.ui.navigation.Navigation
import myapplication.android.musicplayerapp.ui.navigation.NavigationViewModel
import myapplication.android.musicplayerapp.ui.theme.MainGrey
import myapplication.android.musicplayerapp.ui.theme.MusicAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val navController = rememberNavController()
                val viewModel: NavigationViewModel = viewModel()
                val bottomBarVisibleState by viewModel.bottomBarVisible.collectAsState()

                val bottomNavItems = listOf(
                    BottomNavigationItem(
                        title = stringResource(R.string.music),
                        route = MUSIC,
                        icon = R.drawable.ic_music_note_filled
                    ),
                    BottomNavigationItem(
                        title = stringResource(R.string.artists),
                        route = ARTISTS,
                        icon = R.drawable.ic_artist
                    ),
                    BottomNavigationItem(
                        title = stringResource(R.string.playlists),
                        route = PLAYLISTS,
                        icon = R.drawable.ic_playlist_filed
                    )
                )
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MainGrey),
                    color = MainGrey
                ) {
                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(
                                visible = bottomBarVisibleState,
                                modifier = Modifier.background(MainGrey),
                                enter = slideInVertically(
                                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                                    initialOffsetY = { fullHeight -> fullHeight }
                                ),
                                exit = slideOutVertically(
                                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                                    targetOffsetY = { fullHeight -> fullHeight }
                                )
                            ) {
                                BottomNavigationBar(
                                    items = bottomNavItems,
                                    navController = navController,
                                    onClick = {navController.navigate(it.route)}
                                )
                            }
                        }
                    ) { innerPadding ->
                        Navigation(
                            navController = navController,
                            paddings = innerPadding
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val MUSIC = "music"
        const val ARTISTS = "artists"
        const val PLAYLISTS = "playlists"
    }
}
