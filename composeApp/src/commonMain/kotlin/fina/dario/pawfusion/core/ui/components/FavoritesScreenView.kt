package fina.dario.pawfusion.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import co.touchlab.kermit.Logger
import fina.dario.pawfusion.core.BreedFavoritesViewModel
import fina.dario.pawfusion.core.BreedsListViewModel
import fina.dario.pawfusion.core.ui.navigation.voyager.screens.DetailScreen
import fina.dario.pawfusion.models.data.FavoriteBreedModel
import fina.dario.pawfusion.models.data.domain.usecase.GetAllFavoriteBreedsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun FavoritesScreenView(){
    val breedFavoritesViewModel: BreedFavoritesViewModel = koinInject()
    val favorites by breedFavoritesViewModel.favorites.collectAsState()
    val navigator = LocalNavigator.current

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)){
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(items = favorites.breeds){ item ->
                    FavoritesBreedCard(
                        onClick = { navigator?.push(DetailScreen(id = item.id)) },
                        type = item.type,
                        averageLifeSpan = item.averageLifeSpan
                    )
                    }
                }
            }
        }
}
