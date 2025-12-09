package fina.dario.pawfusion.breed.domain.Breed

import io.ktor.util.collections.StringMap

data class Breed(
    val id: String,
    val type: String,
    val attributes: BreedAttributes,
    val life: BreedLife,
)