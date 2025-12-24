package fina.dario.pawfusion.models.data.domain.usecase

import fina.dario.pawfusion.resources.Repository

internal interface DeleteFavoriteBReedByIdUseCase{
    suspend fun deleteFavoriteBreedById(breedId: String)
}

internal class DeleteFavoriteBReedByIdUseCaseImpl(
    private val repository: Repository
): DeleteFavoriteBReedByIdUseCase{
    override suspend fun deleteFavoriteBreedById(breedId: String) {
        repository.deleteFavoriteBreedById(breedId)
    }

}