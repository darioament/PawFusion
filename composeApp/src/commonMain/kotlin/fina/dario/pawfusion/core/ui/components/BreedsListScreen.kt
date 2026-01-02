package fina.dario.pawfusion.core.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import cafe.adriel.voyager.navigator.Navigator
import fina.dario.pawfusion.core.BreedSearchViewModel
import fina.dario.pawfusion.core.BreedsListViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BreedsListScreen(
    navigator: Navigator?,
    onBreedClicked: (String) -> Unit
){
    val focusManager = LocalFocusManager.current
    val breedListViewModel = koinInject<BreedsListViewModel>()
    val breedSearchViewModel = koinInject<BreedSearchViewModel>()
    val isSearching by breedSearchViewModel.isSearching.collectAsState()
    val state by breedListViewModel.state.collectAsState()
    var searchText by remember { mutableStateOf("") }

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AnimatedContent(
                targetState = isSearching,
                modifier = Modifier.fillMaxWidth(),
                transitionSpec = {
                    fadeIn().togetherWith(fadeOut())
                },
                label = "TextFieldAnimation"
            ){ isSearching ->
                if (isSearching){
                    Column(modifier = Modifier.fillMaxWidth()){
                        TextField(
                            value = searchText ,
                            onValueChange = { searchText = it; breedSearchViewModel.onSearchTextChange(searchText)},
                            placeholder = {Text(text = "Search")},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                                .background(shape = RoundedCornerShape(10.dp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions { focusManager.clearFocus() }
                        )
                        BreedsListComponent(
                            state = state,
                            onBreedClicked = onBreedClicked
                        )
                    }

                }
                else{
                    BreedsListComponent(
                        state = state,
                        onBreedClicked = onBreedClicked
                    )
                }
            }

        }
        
    }

}












