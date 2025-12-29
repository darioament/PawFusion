package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fina.dario.pawfusion.core.ui.components.UiBreedListItem
import fina.dario.pawfusion.models.data.FavoriteBreedModel
import fina.dario.pawfusion.models.data.domain.usecase.DeleteFavoriteBReedByIdUseCase
import fina.dario.pawfusion.models.data.domain.usecase.GetAllFavoriteBreedsUseCase
import fina.dario.pawfusion.models.data.domain.usecase.GetAllFavoriteBreedsUseCaseImpl
import fina.dario.pawfusion.models.data.domain.usecase.insertFavoriteBreedUseCase
import fina.dario.pawfusion.models.data.mapper.toFavoriteBreedModel
import fina.dario.pawfusion.models.domain.Breed.BreedFavorites
import fina.dario.pawfusion.resources.LocalDataSourceImpl
import fina.dario.pawfusion.resources.Repository
import fina.dario.pawfusion.resources.RepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

internal class BreedFavoritesViewModel(
    private val getAllFavoriteBreedsUseCase: GetAllFavoriteBreedsUseCase,
    private val InsertFavoriteBreedUseCase: insertFavoriteBreedUseCase,
    private val deleteFavoriteBreedUseCase: DeleteFavoriteBReedByIdUseCase,
): ViewModel() {
    private val _favorites = MutableStateFlow(BreedFavorites())
    val favorites = _favorites.asStateFlow()

    fun loadFavorites(){
        viewModelScope.launch {
            _favorites.update {
                it.copy(
                    breeds = getAllFavoriteBreedsUseCase.invoke() as List<FavoriteBreedModel>
                )
            }
        }
    }

    fun insertFavoriteBreed(breed: UiBreedListItem){
        viewModelScope.launch{
            InsertFavoriteBreedUseCase.insert(
                breed.toFavoriteBreedModel())
        }
    }
}