package fina.dario.pawfusion.breed.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponseDto(
    val data: BreedsListDto
)

@Serializable
data class BreedsListDto(
    val breeds: List<BreedItemDto>
)

@Serializable
data class BreedItemDto(
    val id: String,
    val type: String,
    val attributes: BreedAttributesDto,
    val life: BreedLifeDto,
)

@Serializable
data class BreedAttributesDto(
    val name: String,
    val description: String
)
@Serializable
data class BreedLifeDto(
    val max: Int,
    val min: Int
)

