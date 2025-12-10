package fina.dario.pawfusion.breed.domain.Breed


data class Breed(
    val id: String,
    val type: String,
    val attributes: BreedAttributes,
)