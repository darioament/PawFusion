package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import fina.dario.pawfusion.models.domain.GetBreedsListUseCase
import fina.dario.pawfusion.models.domain.GetBreedDetailsUseCase
import fina.dario.pawfusion.core.ui.components.UiBreedListItem
import fina.dario.pawfusion.core.ui.components.UiBreedWeight
import kotlinx.coroutines.flow.MutableStateFlow
import fina.dario.pawfusion.core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class BreedsListViewModel(
    private val getBreedsListUseCase: GetBreedsListUseCase,
    private val getBreedDetailUseCase: GetBreedDetailsUseCase,
    private val breedFavoriteViewModel: BreedFavoritesViewModel,
    searchEngine: SearchEngineViewModel,
): ViewModel() {

    private val _state = MutableStateFlow(BreedsState())
    private val log = Logger.withTag("PawFusionLogger")
    private val _selectedBreed = MutableStateFlow(makeEmptyBreed())
    val selectedBreed = _selectedBreed.asStateFlow()
    val state: StateFlow<BreedsState> = searchEngine.searchText
        .combine(_state) { text, currentState ->
            log.i("Combine triggered with text: '$text'")
            log.i("BreedsListView search text hash: ${searchEngine.hashCode()}")
            log.i("Combine triggered with breedSearchViewModel.searchText: '${searchEngine.searchText.value}'")
            if (text.isBlank()) {
                currentState
            } else {
                val filteredBreeds = currentState.breeds.filter { breed ->
                    breed.type.contains(text, ignoreCase = true)
                }
                log.i("Filtering completed, found ${filteredBreeds.size} breeds")
                currentState.copy(breeds = filteredBreeds)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )

    init {
        CoroutineScope(Dispatchers.IO).launch {
            launch {
                breedFavoriteViewModel.loadFavorites()
                setLoadingPhase()
                getAllBreeds()
                setLoadingPhaseDone()
            }
        }
    }
    private suspend fun getAllBreeds(){
        when(val breedsResponse = getBreedsListUseCase.execute()){
            is Result.Success -> {
                log.i("Result is success")
                _state.update {
                    BreedsState(
                        breeds = breedsResponse.data.map{breedItem ->
                            UiBreedListItem(
                                id = breedItem.breed.id,
                                type = breedItem.breed.attributes.name,
                                description = breedItem.breed.attributes.description,
                                averageLifeSpan = calculateAverageLifeSpan(
                                    breedItem.breed.attributes.life.min,
                                    breedItem.breed.attributes.life.max
                                ),
                                hypoallergenic = breedItem.breed.attributes.hypoallergenic,
                                male_weight = UiBreedWeight(
                                    min = breedItem.breed.attributes.male_weight.min,
                                    max = breedItem.breed.attributes.male_weight.max,
                                ),
                                female_weight = UiBreedWeight(
                                    min = breedItem.breed.attributes.female_weight.min,
                                    max = breedItem.breed.attributes.female_weight.max,
                                ),
                                isFavorite = isInFavorites(id = breedItem.breed.id) // ovo je problem kod favorite-a
                            )
                        },
                    )
                }
            }
            is Result.Error -> {
                log.i("Result is error in getBreedList")
                _state.update{
                    it.copy(
                        breeds = emptyList(),
                        error = null // TODO: handle breedResponse.error.toUiText()
                    )
                }

            }
        }
    }

    fun getBreedById(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            when (val breedResponse = getBreedDetailUseCase.execute(id)) {
                is Result.Success -> {
                    log.i("Result of catching breedID success in getBreedId2")
                            _selectedBreed.update {
                                it.copy(
                                    id = breedResponse.data.breed.id,
                                    type = breedResponse.data.breed.attributes.name,
                                    description = breedResponse.data.breed.attributes.description,
                                    averageLifeSpan = calculateAverageLifeSpan(breedResponse.data.breed.attributes.life.min, breedResponse.data.breed.attributes.life.max),
                                    hypoallergenic = breedResponse.data.breed.attributes.hypoallergenic,
                                    male_weight = UiBreedWeight(
                                        min = breedResponse.data.breed.attributes.male_weight.min,
                                        max = breedResponse.data.breed.attributes.male_weight.max,
                                    ),
                                    female_weight = UiBreedWeight(
                                        min = breedResponse.data.breed.attributes.female_weight.min,
                                        max = breedResponse.data.breed.attributes.female_weight.max,
                                    ),
                                    isFavorite = isInFavorites(breedResponse.data.breed.id)
                                )
                            }


                }
                is Result.Error -> {
                    log.i("Result is error in getBreedID2")
                }

            }
        }
        log.i("breed in getBreedId2 ${_selectedBreed.value.type}")
    }

    fun toggleFavorite(){
        _selectedBreed.update {
            it.copy(
                isFavorite = !isInFavorites(_selectedBreed.value.id))
        }
        if (_selectedBreed.value.isFavorite){
            breedFavoriteViewModel.insertFavoriteBreed(_selectedBreed.value)
        }else{
            breedFavoriteViewModel.deleteFromFavoriteBreeds(_selectedBreed.value)
        }

        updateStateWhenToggled(_selectedBreed.value.id)
    }
    private fun updateStateWhenToggled(id: String) {
        breedFavoriteViewModel.loadFavorites()
        _state.update { currentState ->
            val updatedBreeds = currentState.breeds.map { breed ->
                if (breed.id == id) {
                    breed.copy(isFavorite = !breed.isFavorite)
                } else {
                    breed
                }
            }
            currentState.copy(breeds = updatedBreeds)
        }
    }

    private fun makeEmptyBreed(): UiBreedListItem {
        return UiBreedListItem(
            id = "",
            type = "Empty",
            description = "Failed to load breed",
            averageLifeSpan = 0,
            hypoallergenic = false,
            male_weight = UiBreedWeight(
                min = 0,
                max = 0,
            ),
            female_weight = UiBreedWeight(
                min = 0,
                max = 0,
            ),
            isFavorite = false,
        )
    }
    private fun isInFavorites(id: String): Boolean {

        log.d("Checking favorite: ${breedFavoriteViewModel.favorites.value.breeds.any { it.id == id }}")
        return breedFavoriteViewModel.favorites.value.breeds.any { it.id == id }
    }

    private fun calculateAverageLifeSpan(min: Int, max: Int) = (min + max) / 2





    private  fun setLoadingPhaseDone() {
        _state.update {
            it.copy(
                loading = false
            )
        }
    }

    private fun setLoadingPhase() {
        _state.update {
            it.copy(
                loading = true
            )
        }
    }

}


