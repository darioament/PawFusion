package fina.dario.pawfusion

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import fina.dario.pawfusion.core.ThemeViewModel
import fina.dario.pawfusion.core.ui.theme.BreedRoutineTheme
import fina.dario.pawfusion.core.ui.navigation.voyager.NavHost
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.favorites.FavoritesTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.home.HomeTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.settings.SettingsTab
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

val myTabs = listOf(
    HomeTab,
    FavoritesTab,
    SettingsTab
)
@Composable
@Preview
fun App() {
    val themeViewModel: ThemeViewModel = koinInject()
    val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()

    Crossfade(targetState = isDarkTheme, animationSpec = tween()){ newTheme ->
        BreedRoutineTheme(newTheme) {
            NavHost()
        }
    }
}

