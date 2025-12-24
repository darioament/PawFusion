package fina.dario.pawfusion.core.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import fina.dario.pawfusion.breed.domain.Breed.BreedFavorites
import fina.dario.pawfusion.breed.domain.GetBreedsListUseCase
import fina.dario.pawfusion.breed.domain.GetBreedDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import fina.dario.pawfusion.core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class BreedsListViewModel(
    private val getBreedsListUseCase: GetBreedsListUseCase,
    private val getBreedDetailUseCase: GetBreedDetailsUseCase
): ViewModel(){

    private val _state = MutableStateFlow(BreedsState())
    private val log = Logger.withTag("PawFusionLogger")
    private val _selectedBreed = MutableStateFlow(makeEmptyBreed())

    val selectedBreed = _selectedBreed.asStateFlow()

    val _favorites = MutableStateFlow(BreedFavorites())
    val favorites = _favorites.asStateFlow()

    val state = _state.asStateFlow().onStart {
        CoroutineScope(Dispatchers.IO).launch {
            launch{
                setLoadingPhase()
                getAllBreeds()
                setLoadingPhaseDone()
            } // napraviti preko init-a
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = BreedsState()
    )


    private suspend fun setLoadingPhaseDone() {
        _state.update {
            it.copy(
                loading = false
            )
        }
    }

    private suspend fun setLoadingPhase() {
        _state.update {
            it.copy(
                loading = true
            )
        }
    }
    fun loadBreedById(id: String){

    }



    private suspend fun getAllBreeds(){
        log.d("Breed in getAllBreeds before update in favorite list has: ${_favorites.value.breeds.size}")
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
                                averageLifeSpan = calculateAverageLifeSpan(breedItem.breed.attributes.life.min, breedItem.breed.attributes.life.max),
                                hypoallergenic = breedItem.breed.attributes.hypoallergenic,
                                male_weight = UiBreedWeight(
                                    min = breedItem.breed.attributes.male_weight.min,
                                    max = breedItem.breed.attributes.male_weight.max,
                                ),
                                female_weight = UiBreedWeight(
                                    min = breedItem.breed.attributes.female_weight.min,
                                    max = breedItem.breed.attributes.female_weight.max,
                                ),
                                isFavorite = if(isInFavorites(id = breedItem.breed.id)) true else false // ovo je problem kod favorite-a
                            )
                        },
                    )
                }
                log.i("Breed in favorite scope ${favorites.value.breeds.size}")
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
                                    isFavorite = favorites.value.breeds.find{it.id == id}?.isFavorite == true // ovo je problem kod favorite-a
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
                isFavorite = !_selectedBreed.value.isFavorite
            )
        }
        if(_selectedBreed.value.isFavorite){
            log.d("Breed is favorite")
            _favorites.update{
                it.copy(
                    breeds = it.breeds + _selectedBreed.value
                )
            }
            log.d("Breed in toggle in favorite list has: ${_favorites.value.breeds.size}")
        }
        else{
            log.d("Breed is not favorite")
        }
        updateStateWhenToggled(_selectedBreed.value.id)
        log.d("Breed type: ${ _selectedBreed.value.type} isFavorite: ${_state.value.breeds.find { it.id == _selectedBreed.value.id }?.isFavorite}")
    }
    private fun updateStateWhenToggled(id: String) {
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
        log.d("Currently in favorites: ${_favorites.value.breeds.size}")
    }

    private fun makeEmptyBreed(): UiBreedListItem{  // -> ovo je potrebno zamijeniti kad se stvara. Dakle potrebno je provjeriti je li zapravo navedeni item favorite
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
        log.d("Checking favorite: ${_favorites.value.breeds.size}")
        return _favorites.value.breeds.any { it.id == id }
    }

    private fun calculateAverageLifeSpan(min: Int, max: Int) = (min + max) / 2

}


