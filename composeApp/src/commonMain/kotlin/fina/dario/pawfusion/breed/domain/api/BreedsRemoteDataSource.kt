package fina.dario.pawfusion.breed.domain.api

import fina.dario.pawfusion.breed.data.remote.dto.BreedsResponseDto
import fina.dario.pawfusion.core.domain.DataError
import fina.dario.pawfusion.core.domain.Result

interface BreedsRemoteDataSource {
    suspend fun getListOfBreeds(): Result<BreedsResponseDto, DataError.Remote>
    suspend fun getBreedById(breedId: String): Result<BreedsResponseDto,DataError.Remote>
}