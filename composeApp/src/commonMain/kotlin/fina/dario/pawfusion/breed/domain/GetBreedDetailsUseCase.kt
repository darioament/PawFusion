package fina.dario.pawfusion.breed.domain

import fina.dario.pawfusion.breed.data.mapper.toBreedModel
import fina.dario.pawfusion.breed.domain.api.BreedsRemoteDataSource
import fina.dario.pawfusion.breed.domain.model.BreedModel
import fina.dario.pawfusion.core.domain.DataError
import fina.dario.pawfusion.core.domain.Result
import fina.dario.pawfusion.core.domain.map

class GetBreedDetailsUseCase(
    private val client: BreedsRemoteDataSource
) {

    suspend fun execute(breedId: String): Result<BreedModel, DataError.Remote>{
        return client.getBreedById(breedId).map { dto ->
            dto.data.breed.toBreedModel()
        }
    }
}
