package fina.dario.pawfusion.voyager

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import fina.dario.pawfusion.breed.presentation.BreedsListScreen

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        BreedsListScreen(navigator){}
    }

}