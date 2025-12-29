package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.compose.koinInject

internal class BreedFavoritesViewModel(
    private val getAllFavoriteBreedsUseCase: GetAllFavoriteBreedsUseCase,
    private val insertFavoriteBreedUseCase: insertFavoriteBreedUseCase,
    private val deleteFavoriteBreedUseCase: DeleteFavoriteBReedByIdUseCase,
): ViewModel() {
    private val _favorites = MutableStateFlow(BreedFavorites())
    private val log = Logger.withTag("PawFusionLogger")
    val favorites = _favorites.asStateFlow()

    fun loadFavorites(){
        CoroutineScope(Dispatchers.IO).launch {
            _favorites.update {
                it.copy(
                    breeds = getAllFavoriteBreedsUseCase.invoke().toSingleList()
                )
            }
            log.d("Favorite breeds in loading phase: ${_favorites.value.breeds.size}")

        }
        log.d("Favorite breeds: ${_favorites.value.breeds.size}")
    }

    fun insertFavoriteBreed(breed: UiBreedListItem){
        log.d("Inserting favorite breed: ${breed.type}")
        CoroutineScope(Dispatchers.IO).launch {
            insertFavoriteBreedUseCase.insert(
                breed.toFavoriteBreedModel())
        }
    }


    fun deleteFromFavoriteBreeds(breed : UiBreedListItem){
        log.d("Deleting favorite breed: ${breed.type}")
        CoroutineScope(Dispatchers.IO).launch{
            deleteFavoriteBreedUseCase.deleteFavoriteBreedById(breed.id)
        }
    }

    private fun <T> Flow<List<T>>.toSingleList(): List<T> = runBlocking {
        this@toSingleList.first() // Suspends until the first list is emitted
    }
}