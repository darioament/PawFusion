package fina.dario.pawfusion.core.ui.navigation.voyager

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import fina.dario.pawfusion.core.ThemeViewModel
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.favorites.FavoritesTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.home.HomeTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.settings.SettingsTab
import org.koin.compose.koinInject

val myTabs = listOf(
    HomeTab,
    FavoritesTab,
    SettingsTab
)
@Composable
fun NavHost(){
    TabNavigator(HomeTab) {
        val themeViewModel: ThemeViewModel = koinInject()
        val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()

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
                                selectedTextColor = if(isDarkTheme) Color.Gray else Color.Black.copy(alpha = 0.7f),
                                selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = Color.Black.copy(alpha = 0.7f),
                                unselectedTextColor = if(isDarkTheme) Color.Gray else Color.Black.copy(alpha = 0.7f),
                                disabledIconColor = MaterialTheme.colorScheme.primaryContainer,
                                disabledTextColor = if(isDarkTheme) Color.Gray else Color.Black.copy(alpha = 0.7f),
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