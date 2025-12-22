package fina.dario.pawfusion.voyager

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import fina.dario.pawfusion.core.components.DetailScreenView
import fina.dario.pawfusion.core.theme.BreedRoutineTheme


class DetailScreen(
    val id: String,
): Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        BreedRoutineTheme {
            DetailScreenView(id = id,  onNavigateBack = { navigator?.pop() })
        }
    }

}

