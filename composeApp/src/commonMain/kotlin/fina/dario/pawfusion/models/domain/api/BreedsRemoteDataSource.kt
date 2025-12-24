package fina.dario.pawfusion.models.domain.api

import fina.dario.pawfusion.models.remote.dto.BreedDetailResponse
import fina.dario.pawfusion.models.remote.dto.BreedsResponseDto
import fina.dario.pawfusion.core.domain.DataError
import fina.dario.pawfusion.core.domain.Result

interface BreedsRemoteDataSource {
    suspend fun getListOfBreeds(): Result<BreedsResponseDto, DataError.Remote>
    suspend fun getBreedById(breedId: String): Result<BreedDetailResponse,DataError.Remote>
}