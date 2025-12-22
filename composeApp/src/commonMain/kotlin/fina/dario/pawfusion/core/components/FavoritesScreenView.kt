package fina.dario.pawfusion.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun FavoritesScreenView(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()){
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(mockedFavoriteList){ item ->
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