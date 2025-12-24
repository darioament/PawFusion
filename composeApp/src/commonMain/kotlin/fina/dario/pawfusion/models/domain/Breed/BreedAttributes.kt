package fina.dario.pawfusion.models.domain.Breed

data class BreedAttributes(
    val name: String,
    val description: String,
    val hypoallergenic: Boolean,
    val life: BreedLife,
    val male_weight: BreedWeight,
    val female_weight: BreedWeight
)