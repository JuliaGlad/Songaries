package myapplication.android.musicplayerapp.ui.screen.general.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralLocalDI

@Module
@InstallIn(ViewModelComponent::class)
class GeneralLocalDiModule {

    @ViewModelScoped
    @Provides
    fun provideGeneralLocalDI(
        tracksRepository: TracksRepository
    ): GeneralLocalDI = GeneralLocalDI(tracksRepository)

}