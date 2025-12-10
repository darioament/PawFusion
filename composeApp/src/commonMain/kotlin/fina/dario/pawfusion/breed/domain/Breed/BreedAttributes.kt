package fina.dario.pawfusion.breed.domain.Breed

data class BreedAttributes(
    val name: String,
    val description: String,
    val hypoallergenic: Boolean,
    val life: BreedLife,
    val male_life: BreedWeight,
    val female_life: BreedWeight
)