package fina.dario.pawfusion.models.domain.Breed

import fina.dario.pawfusion.core.ui.components.UiBreedListItem
import fina.dario.pawfusion.models.data.FavoriteBreedModel

data class BreedFavorites(
    val breeds: List<FavoriteBreedModel> = emptyList()
)
