package fina.dario.pawfusion.models.domain.Breed

import fina.dario.pawfusion.core.ui.components.UiBreedListItem

data class BreedFavorites(
    val breeds: List<UiBreedListItem> = emptyList()
)
