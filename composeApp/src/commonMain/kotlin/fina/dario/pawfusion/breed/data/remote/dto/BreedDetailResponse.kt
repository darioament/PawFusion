package fina.dario.pawfusion.breed.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BreedDetailResponse(
    val data: BreedItemDto
)

@Serializable
data class BreedResponseDto(
    val breed: BreedItemDto
)