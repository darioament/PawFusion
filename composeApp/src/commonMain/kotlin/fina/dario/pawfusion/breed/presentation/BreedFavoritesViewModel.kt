package fina.dario.pawfusion.breed.presentation

import androidx.lifecycle.ViewModel
import fina.dario.pawfusion.breed.domain.Breed.BreedFavorites
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BreedFavoritesViewModel: ViewModel() {
    private val _favorites = MutableStateFlow(BreedFavorites())
    val favorites = _favorites.asStateFlow()


}