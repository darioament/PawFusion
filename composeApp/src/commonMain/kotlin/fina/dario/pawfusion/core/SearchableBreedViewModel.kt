package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel

internal class SearchableBreedViewModel(
    private val breedSearchEngine: SearchEngineViewModel,
    private val breedListViewModel: BreedsListViewModel
): ViewModel() {

}