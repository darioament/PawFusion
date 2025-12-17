package fina.dario.pawfusion.voyager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import fina.dario.pawfusion.core.components.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import fina.dario.pawfusion.breed.presentation.BreedsListScreen
import fina.dario.pawfusion.breed.presentation.BreedsListViewModel
import fina.dario.pawfusion.breed.presentation.LoadingScreen

import fina.dario.pawfusion.core.theme.BreedRoutineTheme
import org.koin.compose.viewmodel.koinViewModel


class HomeScreen: Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val breedListViewModel = koinViewModel<BreedsListViewModel>()
        val state by breedListViewModel.state.collectAsStateWithLifecycle()

        if(state.loading){
            LoadingScreen()
        }
        else{
            BreedRoutineTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(navigator)
                    },
                    bottomBar = {

                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ) {
                                NavigationBarItem(
                                    selected = true,
                                    onClick = { },
                                    label = { Text("Home") },
                                    icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null)},
                                    )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {  },
                                    label = { Text("Favorites") },
                                    icon = { Icon(imageVector = Icons.Filled.Star, contentDescription = null)},
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { navigator?.push(SettingsScreen())  },
                                    label = { Text("Settings") },
                                    icon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = null)},
                                )
                        }
                    }

                    ) { innerPadding ->
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        BreedsListScreen(navigator, onBreedClicked = {itemId->
                            navigator?.push(DetailScreen(id = itemId))
                        })
                    }
                }
            }
        }

    }

}