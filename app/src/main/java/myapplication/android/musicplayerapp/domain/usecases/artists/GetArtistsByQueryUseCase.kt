package myapplication.android.musicplayerapp.domain.usecases.artists

import myapplication.android.musicplayerapp.data.repository.artisits.ArtistsRepository
import myapplication.android.musicplayerapp.domain.mapper.toDomain
import myapplication.android.musicplayerapp.domain.models.ArtistsDomainList
import javax.inject.Inject

class GetArtistsByQueryUseCase @Inject constructor(
    private val repository: ArtistsRepository
){
    suspend fun invoke(query: String, offset: Int) : ArtistsDomainList =
        repository.getArtistsByQuery(query, offset).toDomain()
}