package myapplication.android.musicplayerapp.domain.usecases.artists

import myapplication.android.musicplayerapp.data.repository.artisits.ArtistsRepository
import myapplication.android.musicplayerapp.domain.mapper.toDomain
import myapplication.android.musicplayerapp.domain.models.AlbumsDomainList
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: ArtistsRepository
){
    
    suspend fun invoke(artistId: Int): AlbumsDomainList
    = repository.getArtistAlbums(artistId).toDomain()

}