package fina.dario.pawfusion.models.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponseDto(
    val data: List<BreedItemDto>
)

@Serializable
data class BreedItemDto(
    val id: String,
    val type: String,
    val attributes: BreedAttributesDto,

)

@Serializable
data class BreedAttributesDto(
    val name: String,
    val description: String,
    val hypoallergenic: Boolean,
    val life: BreedLifeDto,
    val male_weight: BreedWeightDto,
    val female_weight: BreedWeightDto,

    )
@Serializable
data class BreedLifeDto(
    val max: Int,
    val min: Int
)


@Serializable
data class BreedWeightDto(
    val min: Int,
    val max: Int
)

