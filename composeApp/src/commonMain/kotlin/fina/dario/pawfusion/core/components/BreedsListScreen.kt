package fina.dario.pawfusion.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
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












