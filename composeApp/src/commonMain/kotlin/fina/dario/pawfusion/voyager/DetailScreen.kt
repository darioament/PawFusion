package fina.dario.pawfusion.voyager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import fina.dario.pawfusion.breed.presentation.DetailScreenView
import fina.dario.pawfusion.core.theme.BreedRoutineTheme


class DetailScreen(
    val id: String,val onNavigateBack: Boolean?
): Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        BreedRoutineTheme {
            DetailScreenView(id = id, onNavigateBack = onNavigateBack)
        }
    }

}

