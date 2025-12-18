package fina.dario.pawfusion.breed.domain.Breed

import fina.dario.pawfusion.breed.presentation.UiBreedListItem

data class BreedFavorites(
    val breeds: List<UiBreedListItem> = emptyList()
)
