package fina.dario.pawfusion

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
//import fina.dario.pawfusion.core.ConnectivityViewModel
import fina.dario.pawfusion.core.ThemeViewModel
import fina.dario.pawfusion.core.ui.navigation.voyager.NavHost
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.favorites.FavoritesTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.home.HomeTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.settings.SettingsTab
import fina.dario.pawfusion.core.ui.theme.BreedRoutineTheme
import kotlinx.coroutines.flow.map
import org.koin.compose.koinInject


@Composable
fun App() {
//    val connectionViewModel: ConnectivityViewModel = koinInject()
//    val connectivityStatus by connectionViewModel.connectivityStatus.collectAsState()

    val themeViewModel: ThemeViewModel = koinInject()
    val prefs: DataStore<Preferences> = koinInject()
    val theme by prefs
        .data.map { dataStore ->
            val themeKey = booleanPreferencesKey("theme")
            dataStore[themeKey]  ?: false
        }.collectAsState(false  )
    LaunchedEffect(theme){
        themeViewModel.setTheme(theme)
    }
    val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()


    Crossfade(targetState = isDarkTheme, animationSpec = tween()){ newTheme ->
        BreedRoutineTheme(isDarkTheme) {
            NavHost()
        }
    }
}

