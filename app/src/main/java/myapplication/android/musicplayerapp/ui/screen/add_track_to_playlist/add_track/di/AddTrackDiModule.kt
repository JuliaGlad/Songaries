package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi.AddTrackLocalDI

@Module
@InstallIn(ViewModelComponent::class)
class AddTrackDiModule {

    @ViewModelScoped
    @Provides
    fun provideAddTrackLocalDI(
        repository: PlaylistRepository
    ): AddTrackLocalDI = AddTrackLocalDI(repository)

}