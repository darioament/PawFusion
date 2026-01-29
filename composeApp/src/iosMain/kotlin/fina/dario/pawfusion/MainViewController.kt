package fina.dario.pawfusion

import androidx.compose.ui.window.ComposeUIViewController
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.di.initKoin
import org.koin.compose.koinInject

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}