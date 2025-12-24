package fina.dario.pawfusion

import androidx.compose.runtime.Composable
import fina.dario.pawfusion.core.theme.BreedRoutineTheme
import fina.dario.pawfusion.voyager.NavHost
import fina.dario.pawfusion.voyager.tab.favorites.FavoritesTab
import fina.dario.pawfusion.voyager.tab.home.HomeTab
import fina.dario.pawfusion.voyager.tab.settings.SettingsTab
import org.jetbrains.compose.ui.tooling.preview.Preview

val myTabs = listOf(
    HomeTab,
    FavoritesTab,
    SettingsTab
)
@Composable
@Preview
fun App() {
    BreedRoutineTheme {
        NavHost()
    }
}

