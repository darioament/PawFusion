package fina.dario.pawfusion.core.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fina.dario.pawfusion.core.BreedsListViewModel
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
    val errorWasShown by breedListViewModel.errorWasShown.collectAsState()
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        breedListViewModel.getBreedById(id)
    }
    val selectedBreed by breedListViewModel.selectedBreed.collectAsState()

    if(state.loading){
        LoadingScreen()
    }
    if(state.error != null && !errorWasShown ){
        showErrorDialog = true
    }
    if(showErrorDialog){
        ErrorDialog(Modifier, state.error.toString(), onDismiss = {showErrorDialog = false; breedListViewModel.clearError()} )
    }
    DetailScreenBody( breed = selectedBreed, onNavigateBack)
}


