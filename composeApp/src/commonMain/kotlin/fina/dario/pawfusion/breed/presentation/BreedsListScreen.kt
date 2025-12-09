package fina.dario.pawfusion.breed.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BreedsListScreen(
    onBreedClicked: (String) -> Unit
){
    val breedListViewModel = viewModel(BreedsListViewModel::class)

    val state by breedListViewModel.state.collectAsStateWithLifecycle(
        initialValue = BreedState()
    )

    BreedsListScreen(
        state = state,
        onBreedClicked = onBreedClicked
    )
}
@Composable
fun BreedsListScreen(
    state: BreedState,
    onBreedClicked: (String) -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().wrapContentHeight()
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
        modifier = Modifier.padding( 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Box(){

        }
    }
}