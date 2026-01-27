package fina.dario.pawfusion.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.navigator.Navigator
import fina.dario.pawfusion.core.SearchEngineViewModel
import fina.dario.pawfusion.core.ThemeViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navigator: Navigator?, scrollBehavior: TopAppBarScrollBehavior) {
    val breedSearchViewModel = koinInject<SearchEngineViewModel>()
    val themeViewModel: ThemeViewModel = koinInject()
    val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "PawFusion",
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight =  MaterialTheme.typography.titleMedium.fontWeight,
                    color = if(isDarkTheme) Color.LightGray else Color.Black.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Find your new friend",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight =  MaterialTheme.typography.titleSmall.fontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = { breedSearchViewModel.setSearching();  }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search icon"
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*navigator?.push(SettingsScreen())*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },)
}