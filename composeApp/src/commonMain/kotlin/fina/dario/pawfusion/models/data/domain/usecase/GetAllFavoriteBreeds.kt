package fina.dario.pawfusion.models.data.domain.usecase

import fina.dario.pawfusion.models.dao.FavoriteBreedEntity
import fina.dario.pawfusion.models.data.FavoriteBreedModel
import fina.dario.pawfusion.models.data.mapper.toFavoriteBreedModel
import fina.dario.pawfusion.resources.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

internal interface GetAllFavoriteBreedsUseCase{
    suspend operator fun invoke(): Flow<List<FavoriteBreedModel>>
}

internal class GetAllFavoriteBreedsUseCaseImpl(
    private val repository: Repository
): GetAllFavoriteBreedsUseCase{
    override suspend fun invoke(): Flow<List<FavoriteBreedModel>> =
        repository.getAllFavoriteBreeds().map{ it ->
            it.map { it.toFavoriteBreedModel() }
        }
}