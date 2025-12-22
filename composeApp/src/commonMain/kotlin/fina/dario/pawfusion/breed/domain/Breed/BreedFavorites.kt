package fina.dario.pawfusion.breed.domain.Breed

import fina.dario.pawfusion.core.components.UiBreedListItem

data class BreedFavorites(
    val breeds: List<UiBreedListItem> = emptyList()
)
