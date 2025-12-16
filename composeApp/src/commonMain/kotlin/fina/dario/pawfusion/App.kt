package fina.dario.pawfusion

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import fina.dario.pawfusion.voyager.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    Navigator(HomeScreen()){navigator ->
        SlideTransition(navigator)
    }
}
