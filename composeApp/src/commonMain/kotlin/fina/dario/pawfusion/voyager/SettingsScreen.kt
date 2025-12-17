package fina.dario.pawfusion.voyager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import fina.dario.pawfusion.core.theme.BreedRoutineTheme

class SettingsScreen: Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        BreedRoutineTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Settings") },
                        navigationIcon = {
                            IconButton(onClick = { navigator?.pop() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                    )
                },
                bottomBar = {

                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ) {
                        NavigationBarItem(
                            selected = false,
                            onClick = { navigator?.push(HomeScreen())  },
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
                            selected = true,
                            onClick = {  },
                            label = { Text("Settings") },
                            icon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = null)},
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ){
                    Text("Settings")
                }
            }
        }
    }
}