package fina.dario.pawfusion.breed.presentation

import androidx.lifecycle.ViewModel
import fina.dario.pawfusion.breed.domain.GetBreedsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import fina.dario.pawfusion.core.domain.Result
import kotlinx.coroutines.flow.update


class BreedsListViewModel(
    private val getBreedsListUseCase: GetBreedsListUseCase,
): ViewModel(){

    private val _state = MutableStateFlow(BreedState())
    val state = _state
        .onStart{
            getAllBreeds()
        }

    private suspend fun getAllBreeds(){
        when(val breedsResponse = getBreedsListUseCase.execute()){
            is Result.Success -> {
                _state.update {
                    BreedState(
                        breeds = breedsResponse.data.map {breedItem ->
                            UiBreedListItem(
                                id = breedItem.breed.id,
                                type = breedItem.breed.type,
                                description = breedItem.breed.attributes.description,
                                averageLifeSpan = calculateAverageLifeSpan(breedItem.breed.life.min, breedItem.breed.life.max)
                            )
                        }
                    )
                }
            }
            is Result.Error -> {
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
