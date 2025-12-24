package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import fina.dario.pawfusion.models.domain.Breed.BreedFavorites
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BreedFavoritesViewModel: ViewModel() {
    private val _favorites = MutableStateFlow(BreedFavorites())
    val favorites = _favorites.asStateFlow()


}