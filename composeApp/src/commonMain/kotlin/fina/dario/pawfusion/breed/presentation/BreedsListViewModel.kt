package fina.dario.pawfusion.breed.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import fina.dario.pawfusion.breed.domain.GetBreedsListUseCase
import fina.dario.pawfusion.breed.domain.GetBreedDetailsUseCase
import fina.dario.pawfusion.breed.domain.model.BreedModel
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
                                averageLifeSpan = calculateAverageLifeSpan(breedItem.breed.attributes.life.min, breedItem.breed.attributes.life.max)
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
                     log.i("Result of catching breedID success")
                     _state.update {
                         it.copy(
                             selectedBreed = UiBreedListItem(
                                 id = breedResponse.data.breed.id,
                                 type = breedResponse.data.breed.attributes.name,
                                 description = breedResponse.data.breed.attributes.description,
                                 averageLifeSpan = calculateAverageLifeSpan(
                                     breedResponse.data.breed.attributes.life.min,
                                     breedResponse.data.breed.attributes.life.max
                                 )
                             )
                         )
                     }

                     log.i("Result is breed_name: ${_state.value.selectedBreed?.type}")
                 }

                 is Result.Error -> {
                     log.i("Result is error in getBreedID")

                     UiBreedListItem(
                         id = "",
                         type = "Error",
                         description = "Failed to load breed",
                         averageLifeSpan = 0
                     )
                 }
             }
         }

    }




    private fun calculateAverageLifeSpan(min: Int, max: Int) = (min + max) / 2

}


