package fina.dario.pawfusion.breed.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import fina.dario.pawfusion.core.components.BreedsListComponent
import fina.dario.pawfusion.core.components.TopAppBar
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BreedsListScreen(
    navigator: Navigator?,
    onBreedClicked: (String) -> Unit
){

    val breedListViewModel = koinViewModel<BreedsListViewModel>()
    val state by breedListViewModel.state.collectAsStateWithLifecycle()

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        BreedsListComponent(
            state = state,
            onBreedClicked = onBreedClicked
        )
    }




}












