package fina.dario.pawfusion.models.domain

import co.touchlab.kermit.Logger
import fina.dario.pawfusion.models.data.mapper.toBreedModel
import fina.dario.pawfusion.models.domain.api.BreedsRemoteDataSource
import fina.dario.pawfusion.models.domain.model.BreedModel
import fina.dario.pawfusion.core.domain.DataError

import fina.dario.pawfusion.core.domain.Result
import fina.dario.pawfusion.core.domain.map

class GetBreedsListUseCase(
    private val client: BreedsRemoteDataSource
){
    private val log = Logger.withTag("PawFusionLogger")
    suspend fun execute(): Result<List<BreedModel>, DataError.Remote>{
        log.i("GetBreedsListUseCase.execute called")
        return client.getListOfBreeds().map { dto ->
            dto.data.map { it.toBreedModel() }
        }

    }
}