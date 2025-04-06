package myapplication.android.musicplayerapp.ui.local_composition

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi


@Module
@InstallIn(ViewModelComponent::class)
class LocalPlaylistModule {

    @ViewModelScoped
    @Provides
    fun provideLocalPlaylists(): SnapshotStateList<PlaylistUi> = mutableStateListOf()

}