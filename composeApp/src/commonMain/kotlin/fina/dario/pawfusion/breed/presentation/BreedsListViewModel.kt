package fina.dario.pawfusion.breed.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import fina.dario.pawfusion.breed.domain.GetBreedsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import fina.dario.pawfusion.core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class BreedsListViewModel(
    private val getBreedsListUseCase: GetBreedsListUseCase,
): ViewModel(){

    private val _state = MutableStateFlow(BreedState())
    private val log = Logger.withTag("PawFusionLogger")
    val state = _state.asStateFlow().onStart {
        CoroutineScope(Dispatchers.IO).launch {
            launch{
                setLoadingPhase()
                getAllBreeds()
                setLoadingPhaseDone()
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = BreedState()
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
                    BreedState(
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


