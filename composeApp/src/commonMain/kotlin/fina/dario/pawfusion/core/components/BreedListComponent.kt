package fina.dario.pawfusion.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fina.dario.pawfusion.breed.presentation.BreedState

@Composable
fun BreedsListComponent(
    state: BreedState,
    onBreedClicked: (String) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize().padding(all = 20.dp),
        contentAlignment = Alignment.Center,
    ){
        BreedList(
            breeds = state.breeds,
            onBreedClicked = onBreedClicked
        )
    }
}

