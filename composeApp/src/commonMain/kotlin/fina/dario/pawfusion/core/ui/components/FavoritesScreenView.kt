package fina.dario.pawfusion.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import fina.dario.pawfusion.core.BreedFavoritesViewModel
import fina.dario.pawfusion.models.data.FavoriteBreedModel
import fina.dario.pawfusion.models.data.domain.usecase.GetAllFavoriteBreedsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun FavoritesScreenView(){
    val scope = rememberCoroutineScope()
    val breedFavoritesViewModel: BreedFavoritesViewModel = koinInject()
    breedFavoritesViewModel.loadFavorites()

    val favorites by breedFavoritesViewModel.favorites.collectAsState()

    // problem is in favorites are not empty list or there is a casting problem

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()){
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(items = favorites.breeds){ item ->
                    FavoritesBreedCard(item.type, item.averageLifeSpan)
                    }
                }
            }
        }
    }


val mockedFavoriteList = listOf(
    UiBreedListItem(
        id = "1",
        type ="Affenpinscher",
        description = "Small and playful dog",
        averageLifeSpan = 15,
        hypoallergenic = true,
        male_weight = UiBreedWeight(5,10),
        female_weight = UiBreedWeight(4,9),
        isFavorite = true
    ),
    UiBreedListItem(
        id = "2",
        type ="Afghan Hound",
        description = "Small and playful dog",
        averageLifeSpan = 15,
        hypoallergenic = false,
        male_weight = UiBreedWeight(5,10),
        female_weight = UiBreedWeight(4,9),
        isFavorite = true
    ),
    UiBreedListItem(
        id = "3",
        type ="Airedale Terrier",
        description = "Small and playful dog",
        averageLifeSpan = 15,
        hypoallergenic = true,
        male_weight = UiBreedWeight(5,10),
        female_weight = UiBreedWeight(4,9),
        isFavorite = true
    ),
    UiBreedListItem(
        id = "4",
        type ="Akita",
        description = "Small and playful dog",
        averageLifeSpan = 15,
        hypoallergenic = true,
        male_weight = UiBreedWeight(5,10),
        female_weight = UiBreedWeight(4,9),
        isFavorite = true
    ),
)