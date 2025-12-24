package fina.dario.pawfusion.core.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DetailScreenView(
    id: String,
    onNavigateBack: () -> Unit,
){

    val breedListViewModel = koinViewModel<BreedsListViewModel>()
    val state by breedListViewModel.state.collectAsState()

    LaunchedEffect(Unit){
        breedListViewModel.getBreedById(id)
    }
    val selectedBreed by breedListViewModel.selectedBreed.collectAsState()

    if(state.loading){
        LoadingScreen()
    }

    DetailScreenBody( breed = selectedBreed, onNavigateBack)
}


