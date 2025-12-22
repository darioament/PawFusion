package fina.dario.pawfusion.voyager.tab.home

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
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import fina.dario.pawfusion.breed.presentation.BreedsListScreen
import fina.dario.pawfusion.breed.presentation.BreedsListViewModel
import fina.dario.pawfusion.breed.presentation.LoadingScreen
import fina.dario.pawfusion.core.components.TopAppBar
import fina.dario.pawfusion.core.theme.BreedRoutineTheme
import fina.dario.pawfusion.voyager.DetailScreen
import fina.dario.pawfusion.voyager.FavoritesScreen
import fina.dario.pawfusion.voyager.HomeScreen
import fina.dario.pawfusion.voyager.SettingsScreen
import org.koin.compose.viewmodel.koinViewModel

object HomeTab : Tab {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(){
        Navigator(HomeScreen()) { navigator ->
            SlideTransition(navigator)
        }

    }
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "Home"
            )
        }
}