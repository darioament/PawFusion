package fina.dario.pawfusion.core

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import fina.dario.pawfusion.models.domain.Breed.BreedFavorites
import fina.dario.pawfusion.models.domain.GetBreedsListUseCase
import fina.dario.pawfusion.models.domain.GetBreedDetailsUseCase
import fina.dario.pawfusion.core.ui.components.UiBreedListItem
import fina.dario.pawfusion.core.ui.components.UiBreedWeight
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import fina.dario.pawfusion.core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay

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
    breedSearchViewModel: BreedSearchViewModel,
): ViewModel() {

    private val _state = MutableStateFlow(BreedsState())
    private val log = Logger.withTag("PawFusionLogger")
    private val _selectedBreed = MutableStateFlow(makeEmptyBreed())
    private val searchText: StateFlow<String> = breedSearchViewModel.searchText


    val selectedBreed = _selectedBreed.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            launch {
                setLoadingPhase()
                getAllBreeds()
                setLoadingPhaseDone()
            }
        }
    }

    val state: StateFlow<BreedsState> = searchText
        .combine(_state) { text, currentState ->
            if (text.isEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    getAllBreeds()
                    log.i("Text is empty in getBreedList so calling getAllBreeds")
                }
                currentState
            } else {

                val filteredBreeds = currentState.breeds.filter { breed ->
                    breed.type.contains(text, ignoreCase = true)
                }
                log.i("Text is not empty in getBreedList so filtering breeds")
                currentState.copy(breeds = filteredBreeds)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )




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

    suspend fun getBreedById(id: String){
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
            // 2. Kreiranje novog stanja sa ažuriranom listi -> provjereno u logcat-u (RADI). Ne display-a dobro jer vuce podatke s api-a i onda su automatski false
            currentState.copy(breeds = updatedBreeds)
        }
    }

    private fun makeEmptyBreed(): UiBreedListItem {  // -> ovo je potrebno zamijeniti kad se stvara. Dakle potrebno je provjeriti je li zapravo navedeni item favorite
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

}


