package fina.dario.pawfusion.breed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import fina.dario.pawfusion.breed.domain.GetBreedsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import fina.dario.pawfusion.core.domain.Result

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class BreedsListViewModel(
    private val getBreedsListUseCase: GetBreedsListUseCase,
): ViewModel(){

    private val _state = MutableStateFlow(BreedState())
    private val log = Logger.withTag("PawFusionLogger")
    val state = _state
        .onStart{
            getAllBreeds()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BreedState()
        )

    private suspend fun getAllBreeds(){
        when(val breedsResponse = getBreedsListUseCase.execute()){
            is Result.Success -> {
                log.i("Result is success")
                _state.update {
                    BreedState(
                        breeds = breedsResponse.data.map {breedItem ->
                            UiBreedListItem(
                                id = breedItem.breed.id,
                                type = breedItem.breed.type,
                                description = breedItem.breed.attributes.description,
                                averageLifeSpan = calculateAverageLifeSpan(breedItem.breed.attributes.life.min, breedItem.breed.attributes.life.max)
                            )
                        }
                    )
                }
            }
            is Result.Error -> {
                log.i("Result is error")
                _state.update{
                    it.copy(
                        breeds = emptyList(),
                        error = null // TODO: handle breedResponse.error.toUiText()
                    )
                }

            }
        }
    }

    private fun calculateAverageLifeSpan(min: Int, max: Int) = (min + max) / 2

}


