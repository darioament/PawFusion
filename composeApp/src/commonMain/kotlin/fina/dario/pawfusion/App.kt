package fina.dario.pawfusion

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import fina.dario.pawfusion.breed.presentation.DetailScreenView
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
    BreedRoutineTheme {
        TabNavigator(HomeTab) {
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        myTabs.forEach { tab ->
                            val tabNavigator = LocalTabNavigator.current
                            NavigationBarItem(
                                selected = tabNavigator.current == tab,
                                onClick = { tabNavigator.current = tab },
                                icon = { },
                                label = { Text(tab.options.title) },
                                colors = NavigationBarItemColors(
                                    selectedIconColor = Color.Black.copy(alpha = 0.7f),
                                    selectedTextColor = Color.Black.copy(alpha = 0.7f),
                                    selectedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                                    unselectedIconColor = Color.Black.copy(alpha = 0.7f),
                                    unselectedTextColor = Color.Black.copy(alpha = 0.7f),
                                    disabledIconColor = MaterialTheme.colorScheme.outlineVariant,
                                    disabledTextColor = MaterialTheme.colorScheme.outlineVariant
                                ),
                            )

                        }
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}

