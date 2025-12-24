package fina.dario.pawfusion.models.data.domain.usecase

import fina.dario.pawfusion.models.data.FavoriteBreedModel
import fina.dario.pawfusion.models.data.mapper.toFavoriteBreedModel
import fina.dario.pawfusion.resources.Repository

internal interface GetFavoriteBreedById{
    suspend fun getFavoriteBreedById(breedId: String): FavoriteBreedModel?
}


internal class GetFavoriteBreedByIdImpl(
    private val repository: Repository
): GetFavoriteBreedById{
    override suspend fun getFavoriteBreedById(breedId: String): FavoriteBreedModel? =
        repository.getFavoriteBreedById(breedId)?.toFavoriteBreedModel()
}