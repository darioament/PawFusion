package fina.dario.pawfusion.models.domain

import fina.dario.pawfusion.models.data.mapper.toBreedModel
import fina.dario.pawfusion.models.domain.api.BreedsRemoteDataSource
import fina.dario.pawfusion.models.domain.model.BreedModel
import fina.dario.pawfusion.core.domain.DataError
import fina.dario.pawfusion.core.domain.Result
import fina.dario.pawfusion.core.domain.map
import co.touchlab.kermit.Logger

class GetBreedDetailsUseCase(
    private val client: BreedsRemoteDataSource
) {
    private val log = Logger.withTag("PawFusionLogger")

    suspend fun execute(breedId: String): Result<BreedModel, DataError.Remote>{
        log.i("Execute of mapper")
        return client.getBreedById(breedId).map { dto ->
            log.i("Map of mapper")
            dto.data.toBreedModel()
        }
    }
}
