package fina.dario.pawfusion.voyager.tab.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import fina.dario.pawfusion.voyager.HomeScreen

object HomeTab : Tab {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(){
        Navigator(HomeScreen()) { navigator ->
            SlideTransition(navigator)
        }

    }
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "Home"
            )
        }
}