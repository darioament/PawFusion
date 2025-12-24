package fina.dario.pawfusion.models.remote.impl

import co.touchlab.kermit.Logger
import fina.dario.pawfusion.models.remote.dto.BreedDetailResponse
import fina.dario.pawfusion.models.remote.dto.BreedsResponseDto
import fina.dario.pawfusion.models.domain.api.BreedsRemoteDataSource
import fina.dario.pawfusion.core.domain.Result
import fina.dario.pawfusion.core.domain.DataError
import fina.dario.pawfusion.core.network.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get


private const val BASE_URL = "https://dogapi.dog/api/v2"
class KtorBreedsRemoteDataSource(
    private val httpClient: HttpClient
): BreedsRemoteDataSource {
    private val log = Logger.withTag("PawFusionLogger")
    override suspend fun getListOfBreeds(): Result<BreedsResponseDto, DataError.Remote> {
        log.i("KtorBreedsRemoteDataSource.getListOfBreeds called")
        return safeCall {
            httpClient.get("$BASE_URL/breeds")
        }
    }

    override suspend fun getBreedById(breedId: String): Result<BreedDetailResponse, DataError.Remote>{
        return safeCall {
            httpClient.get("$BASE_URL/breeds/{$breedId}")
        }

    }
}