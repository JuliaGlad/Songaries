package myapplication.android.musicplayerapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepositoryImpl
import myapplication.android.musicplayerapp.data.source.local.playlists.PlaylistLocalSource
import myapplication.android.musicplayerapp.data.source.local.playlists.PlaylistLocalSourceImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class PlaylistsModule {
    @ViewModelScoped
    @Binds
    abstract fun bindPlaylistsRepository(playlistRepositoryImpl: PlaylistRepositoryImpl): PlaylistRepository

    @ViewModelScoped
    @Binds
    abstract fun bindPlaylistLocalSource(playlistsLocalSourceImpl: PlaylistLocalSourceImpl): PlaylistLocalSource
}