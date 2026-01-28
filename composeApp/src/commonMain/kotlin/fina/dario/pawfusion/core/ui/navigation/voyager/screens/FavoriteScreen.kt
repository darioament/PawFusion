package fina.dario.pawfusion.core.ui.navigation.voyager.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import fina.dario.pawfusion.core.ui.components.FavoritesScreenView

class FavoriteScreen: Screen {
    @Composable
    override fun Content() {
        FavoritesScreenView()
    }
}