package fina.dario.pawfusion.breed.presentation

import fina.dario.pawfusion.breed.domain.Breed.BreedAttributes
import fina.dario.pawfusion.breed.domain.Breed.BreedLife

data class UiBreedListItem(
    val id: String,
    val type: String,
    val description: String,
    val averageLifeSpan: Int,
)