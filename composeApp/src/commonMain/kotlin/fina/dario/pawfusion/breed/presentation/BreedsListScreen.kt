package fina.dario.pawfusion.breed.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import fina.dario.pawfusion.TopAppBar
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BreedsListScreen(
    navigator: Navigator?,
    onBreedClicked: (String) -> Unit
){

    val breedListViewModel = koinViewModel<BreedsListViewModel>()

    val state by breedListViewModel.state.collectAsStateWithLifecycle()

    if(state.loading){LoadingScreen()}
    else{
        TopAppBar(navigator)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            BreedsListComponent(
                state = state,
                onBreedClicked = onBreedClicked
            )
        }

    }


}



@Composable
fun BreedsListComponent(
    state: BreedState,
    onBreedClicked: (String) -> Unit
){
    Box(
        modifier = Modifier.height(455.dp).width(341.dp),
        contentAlignment = Alignment.Center,
    ){
        BreedList(
            breeds = state.breeds,
            onBreedClicked = onBreedClicked
        )
    }
}

@Composable
fun BreedList(
    breeds: List<UiBreedListItem>,
    onBreedClicked: (String) -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().wrapContentHeight()
    ){
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(breeds){ breed ->
                BreedListItem(
                    breed = breed,
                    onBreedClicked
                )
            }
        }
    }
}

@Composable
fun BreedListItem(
    breed: UiBreedListItem,
    onBreedClicked: (String) -> Unit
){
    Card(
        modifier = Modifier
            .height(455.dp).width(341.dp)
            .padding( 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()){
                Text(
                        text = breed.type,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = breed.description,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Average lifespan: ${breed.averageLifeSpan}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,

                )
            }

        }
    }
}