package fina.dario.pawfusion.core.components

data class UiBreedListItem(
    val id: String,
    val type: String,
    val description: String,
    val averageLifeSpan: Int,
    val hypoallergenic: Boolean,
    val male_weight: UiBreedWeight,
    val female_weight: UiBreedWeight,
    var isFavorite: Boolean,
)

data class UiBreedWeight(
    val min: Int,
    val max: Int,
)
