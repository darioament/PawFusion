package fina.dario.pawfusion.core.ui.navigation.voyager.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import fina.dario.pawfusion.core.ThemeViewModel
import fina.dario.pawfusion.core.ui.components.DetailScreenView
import org.koin.compose.koinInject


class DetailScreen(
    val id: String,
): Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        DetailScreenView(id = id,  onNavigateBack = { navigator?.pop() })
    }
}

