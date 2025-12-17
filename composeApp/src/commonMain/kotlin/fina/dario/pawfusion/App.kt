package fina.dario.pawfusion

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import fina.dario.pawfusion.core.theme.BreedRoutineTheme
import fina.dario.pawfusion.voyager.HomeScreen
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
    Navigator(HomeScreen()) { navigator ->
        SlideTransition(navigator)

    }
}

