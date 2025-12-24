package fina.dario.pawfusion.models.data.domain.usecase

import fina.dario.pawfusion.models.data.FavoriteBreedModel
import fina.dario.pawfusion.models.data.mapper.toFavoriteBreedEntity
import fina.dario.pawfusion.resources.Repository

internal interface insertFavoriteBreedUseCase{
    suspend fun insert(favoriteBreedModel: FavoriteBreedModel)
}

internal class insertFavoriteBreedUseCaseImpl(
    private val repository: Repository
): insertFavoriteBreedUseCase{
    override suspend fun insert(favoriteBreedModel: FavoriteBreedModel) =
        repository.insert(favoriteBreedModel.toFavoriteBreedEntity())
}